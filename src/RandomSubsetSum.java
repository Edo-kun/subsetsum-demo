/**
 * File: RandomSubsetSum.java
 * @author Shawn Jiang dis guy
 * @author Alex Rinker love 3rd roommates
 * @author Ed Zhou ... guy
 * @author Mathias loves sour cream
 * Class: CS375
 * Project: 4
 * Date: April 15 2017
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSubsetSum implements ApproxSubsetSum{

    /**
     * Randomly finds subsets to acquire approximate distance from targetSum
     * @param list list to find subsets
     * @param targetSum the desired sum
     * @param iterations iterations
     * @return
     */
    public Long containsSubsetSum(List<Long> list, Long targetSum, int iterations) {
        Random random = new Random();
        Long min = Long.MAX_VALUE;
        for (int i = 0; i < iterations; i++) {
            ArrayList<Long> baseList = new ArrayList(list);
            ArrayList<Long> tempSubset= new ArrayList();
            int randVal = random.nextInt(baseList.size());
            for (int j = 0; j < randVal; j++) {
                int randIndex = random.nextInt(baseList.size());
                tempSubset.add(baseList.remove(randIndex));
            }
            Long tempMin = Math.abs(targetSum - tempSubset.stream().mapToLong(Long::longValue).sum());
            if ( tempMin < min) {
                min = tempMin;
            }
        }
        return min;
    }

    public static void main(String args[]) {
        RandomSubsetSum randomSubsetSum = new RandomSubsetSum();
        ArrayList<Long> list = new ArrayList();
        Random random = new Random();
        Double l = new Double("1000000000000");
        for(int i = 0; i < 100; i++) {
            list.add(((Double)(random.nextDouble() * l)).longValue());
        }
        System.out.println(randomSubsetSum.containsSubsetSum(list, ((Double)(25 * l)).longValue(), 1000));
    }
}
