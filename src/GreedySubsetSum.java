/**
 * File: GreedySubsetSum.java
 * @author Shawn Jiang was beat by the claw
 * @author Alex Rinker hates the claw
 * @author Ed Zhou is not here
 * @author Mathias the promiscuous
 * Class: CS375
 * Project: 4
 * Date: April 15 2017
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GreedySubsetSum {
    /**
     * Finds Approximation of target sum through the Greedy algorithm
     * @param list the list
     * @param targetSum desired sum
     * @return
     */
    public Long containsSubsetSum(List<Long> list, Long targetSum) {
        ArrayList<Long> subset = new ArrayList<>();
        Collections.sort(list);
        Collections.reverse(list);
        Long sum = new Long(0);
        for (int i = 0; i < list.size(); i++) {
            if (sum + list.get(i) <= targetSum) {
                subset.add(list.get(i));
                sum += list.get(i);
            }
        }
        return targetSum - sum;
    }

    public static void main(String args[]) {
        GreedySubsetSum greedySubsetSum = new GreedySubsetSum();
        ArrayList<Long> list = new ArrayList<>();
        Random random = new Random();
        Double l = new Double("1000000000000");
        for(int i = 0; i < 100; i++) {
            list.add(((Double)(random.nextDouble() * l)).longValue());
        }
        System.out.println(greedySubsetSum.containsSubsetSum(list, ((Double)(25 * l)).longValue()));
    }
}
