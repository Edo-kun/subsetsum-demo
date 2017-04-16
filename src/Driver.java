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

/**
 * A driver for testing.
 */

public class Driver {
    public static void main(String[] args) throws Exception {
        // tests
        test("Exhaustive Search", new ExhaustiveSubsetSum());

    }

    private static void test(String version, SubsetSum tester) throws Exception {
        // warm-up

        // compute
        Timer.start();
        //---
        Timer.stop();

        // output the results
        System.out.println("--------" + version + "----------");

        // output the time required to run
        System.out.println("Time: " + Timer.getRuntime() + "ms");

        System.out.println();
    }
}