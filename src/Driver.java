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
        int[] valueLimits = {6, 11, 16, 21};
        for (int i = 0; i < valueLimits.length; i++) {
            testExact(new ExhaustiveSubsetSum(), valueLimits[i]);
        }
        valueLimits = new int[] {26, 51, 101, 201, 401, 1001};
        for (int i = 0; i < valueLimits.length; i++) {
            testExact(new DynamicSubsetSum(), valueLimits[i]);
        }
        // test approximate algorithms with varying inputs
        System.out.println("|-------------------------Varying Approx---------------------------|");

        valueLimits = new int[] {1000, 2000, 5000, 10000};
        Random random = new Random();

        for (int i = 1; i < valueLimits.length; i++) {
            ArrayList<Long> list = new ArrayList<>();
            for (int j = 0; j < valueLimits[i]; j++) {
                list.add(new Long(random.nextInt(10000)));
            }
            testApprox(new GreedySubsetSum(), list, new Long(10000), 100, true);
            testApprox(new RandomSubsetSum(), list, new Long(10000), 100, true);
            testApprox(new HillClimbingSubsetSum(), list, new Long(10000), 100, true);
            testApprox(new SimAnnealSubsetSum(), list, new Long(10000), 100, true);
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
        System.out.println(largeInputResults);
    }

    /**
     * Tests exact versions of the subset problem
     * @param tester
     * @throws Exception
     */
    private static void testExact(SubsetSum tester, int valueLimit) throws Exception {
        // warm-up
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < valueLimit; i++) {
            list.add(new Integer(i));
        }

        // compute
        Timer.start();
        tester.containsSubsetSum(list, 10);
        Timer.stop();

        // output the results
        System.out.println("--------" + tester.getClass().getName() + "---------- for " +(valueLimit-1)+ " values");

        // output the time required to run
        System.out.println("Time: " + Timer.getRuntime() + "ms");

        System.out.println();

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
        if (timing) {
            Timer.start();
        }
        Long l = algorithm.containsSubsetSum(list, target, iterations);
        if (timing) {
            Timer.stop();
            System.out.println("--------" + algorithm.getClass().getName() + "---------- for " +list.size() + " values");

            // output the time required to run
            System.out.println("Time: " + Timer.getRuntime() + "ms");

            System.out.println();
        }
        return l;
    }
}