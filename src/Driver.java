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

/**
 * A driver for testing.
 */

public class Driver {
    public static void main(String[] args) throws Exception {
        // tests
        testExact("Exhaustive Search", new ExhaustiveSubsetSum());
        testExact("Dynamic Search", new DynamicSubsetSum());

    }

    private static void testExact(String version, SubsetSum tester) throws Exception {
        // warm-up
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i < 23; i++) {
            list.add(new Integer(i));
        }
        // compute
        Timer.start();
        tester.containsSubsetSum(list, 100);
        Timer.stop();

        // output the results
        System.out.println("--------" + version + "----------");

        // output the time required to run
        System.out.println("Time: " + Timer.getRuntime() + "ms");

        System.out.println();
    }
}