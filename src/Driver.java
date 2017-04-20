/**
 * File: Driver.java
 * @author Shawn Jiang
 * @author Alex Rinker
 * @author Ed Zhou
 * @author Mathias "DromeStrikeCraw" Syndrome the Kallick
 * Class: CS375
 * Project: 4
 * Date: April 15 2017
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A driver for testing.
 */

public class Driver {
    public static void main(String[] args) throws Exception {
        // tests exact
        System.out.println("|------------------------Exact Algorithms--------------------------|");
        timeExactAlgorithms();
        // test approximate algorithms with varying inputs
        System.out.println("|------------------------Approx Algorithms--------------------------|");
        timeApproxAlgorithms();
        System.out.println("|-----------------------Accuracy of Approx-------------------------|");
        testApproxAccuracy();
    }

    private static void timeExactAlgorithms() throws Exception{
        // test exhaustive algorithm
        int[] valueLimits = {16, 21, 26};
        for (int i = 0; i < valueLimits.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 1; j < valueLimits[i]; j++) {
                list.add(new Integer(i));
            }
            System.out.println("---Testing for " + (valueLimits[i]-1) + " values---");
            testExact(new ExhaustiveSubsetSum(), list);
        }
        // test dynamic subset sum
        valueLimits = new int[] {2501, 5001, 10001, 20001};
        for (int i = 0; i < valueLimits.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 1; j < valueLimits[i]; j++) {
                list.add(new Integer(i));
            }
            System.out.println("---Testing for " + (valueLimits[i]-1) + " values---");
            testExact(new DynamicSubsetSum(), list);
        }
    }

    /**
     * Tests exact versions of the subset problem
     * @param tester
     * @throws Exception
     */
    private static void testExact(SubsetSum tester, List<Integer> list) throws Exception {
        // warm-up

        // compute
        Timer.start();
        tester.containsSubsetSum(list, list.size()*2);
        Timer.stop();

        // output the results
        System.out.println(tester.getClass().getName() +" ran at " + Timer.getRuntime() + "ms");
    }

    private static void timeApproxAlgorithms() {
        int[] valueLimits = new int[] {10000, 20000, 50000, 100000};
        int[] iterationLimits = new int[] {1000, 2000, 5000};
        Random random = new Random();

        // test list size for input variation
        for (int i = 0; i < valueLimits.length; i++) {
            ArrayList<Long> list = new ArrayList<>();
            for (int j = 0; j < valueLimits[i]; j++) {
                list.add(new Long(random.nextInt(valueLimits[i])));
            }
            int repetitions = 100;
            Long target = new Long(valueLimits[i]*2);
            System.out.println("---Testing for " + (valueLimits[i]) + " values at " + repetitions + " iterations---");
            testApprox(new GreedySubsetSum(), list, target, repetitions, true);
            testApprox(new RandomSubsetSum(), list, target, repetitions, true);
            testApprox(new HillClimbingSubsetSum(), list, target, repetitions, true);
            testApprox(new SimAnnealSubsetSum(), list, target, repetitions, true);
        }

        // test iterations for input variations
        int fixedSize = 10000;
        for (int i = 0; i < iterationLimits.length; i++) {
            ArrayList<Long> list = new ArrayList<>();
            for (int j = 0; j < valueLimits[i]; j++) {
                list.add(new Long(random.nextInt(fixedSize)));
            }

            Long target = new Long(fixedSize * 2);
            System.out.println("---Testing for " + fixedSize + " values at " + iterationLimits[i] + " iterations---");
            testApprox(new GreedySubsetSum(), list, target, iterationLimits[i], true);
            testApprox(new RandomSubsetSum(), list, target, iterationLimits[i], true);
            testApprox(new HillClimbingSubsetSum(), list, target, iterationLimits[i], true);
            testApprox(new SimAnnealSubsetSum(), list, target, iterationLimits[i], true);
        }
    }

    private static void testApproxAccuracy() {
        ArrayList<Long> list;
        Random random = new Random();

        System.out.println("|--------------------------Large Inputs----------------------------|");

        Double l = new Double("1000000000000");
        Long targetSum = ((Double)(25 * l)).longValue();

        ArrayList<ArrayList<Long>> largeInputResults = new ArrayList<>();
        // create a list for each algorithm,
        for (int i = 0; i < 4; i++) {
            largeInputResults.add(new ArrayList());
        }
        for (int i = 0; i < 50; i++) {
            list = new ArrayList<>();
            for(int j = 0; j < 100; j++) {
                list.add(((Double)(random.nextDouble() * l)).longValue());
            }
            largeInputResults.get(0).add(testApprox(new GreedySubsetSum(), list, targetSum, 1000, false));
            largeInputResults.get(1).add(testApprox(new RandomSubsetSum(), list, targetSum, 1000, false));
            largeInputResults.get(2).add(testApprox(new HillClimbingSubsetSum(), list, targetSum, 1000, false));
            largeInputResults.get(3).add(testApprox(new SimAnnealSubsetSum(), list, targetSum, 1000, false));
        }
        System.out.println("Greedy Sum Results");
        System.out.println(largeInputResults.get(0));
        System.out.println(largeInputResults.get(0).stream().mapToLong(Long::longValue).sum()/largeInputResults.get(0).size());
        System.out.println("Random Results");
        System.out.println(largeInputResults.get(1));
        System.out.println(largeInputResults.get(1).stream().mapToLong(Long::longValue).sum()/largeInputResults.get(1).size());
        System.out.println("Hill Climbing Results");
        System.out.println(largeInputResults.get(2));
        System.out.println(largeInputResults.get(2).stream().mapToLong(Long::longValue).sum()/largeInputResults.get(2).size());
        System.out.println("Sim Anneal Results");
        System.out.println(largeInputResults.get(3));
        System.out.println(largeInputResults.get(3).stream().mapToLong(Long::longValue).sum()/largeInputResults.get(3).size());

    }

    /**
     * Method for testing approximations
     * @param algorithm the approx algorithm
     * @param list the list of longs
     * @param target the target sum
     * @param iterations iterations the algorithm will be run
     * @param timing if you want the time to be printed
     * @return
     */
    private static Long testApprox(ApproxSubsetSum algorithm,
                                   List<Long> list,
                                   Long target,
                                   int iterations,
                                   boolean timing) {
        // warmup

        algorithm.containsSubsetSum(list, target, iterations);
        if (timing) {
            Timer.start();
        }
        Long l = algorithm.containsSubsetSum(list, target, iterations);
        if (timing) {
            Timer.stop();
            System.out.println(algorithm.getClass().getName() + " ran at " + Timer.getRuntime() + "ms");

        }
        return l;
    }
}