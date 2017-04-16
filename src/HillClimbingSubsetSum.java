/**
 * File: HillClimbingSubsetSum.java
 * @author Jiang Shawn
 * @author Alex da sinker
 * @author Eddy go bo cho
 * @author My mushrroms thias
 * Class: CS375
 * Project: 4
 * Date: April 15 2017
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HillClimbingSubsetSum {
    /**
     * Hill Climbing algorithm
     * @param list the list
     * @param targetSum the desired sum
     * @param iterations number of iterations
     * @return
     */
    public Long containsSubsetSum(List<Long> list, Long targetSum, int iterations) {
        Random random = new Random();
        ArrayList<Long> baseList = new ArrayList(list);
        ArrayList<Long> current = new ArrayList();

        // find random sublist
        int randVal = random.nextInt(baseList.size());
        for (int j = 0; j < randVal; j++) {
            int randIndex = random.nextInt(baseList.size());
            current.add(baseList.remove(randIndex));
        }

        Long min = Long.MAX_VALUE;
        for (int r = 0; r < iterations; r++) {
            // find neighbor
            // 1. Order the elements of S as {x1, x2, ..., xn}
            // 2. initialize t to be a clone of s
            ArrayList<Long> s = new ArrayList(current);
            ArrayList<Long> t = new ArrayList(s);

            // 3. choose two random indices
            int i = 0;
            int j = 0;
            while (i == j) {
                i = random.nextInt(list.size());
                j = random.nextInt(list.size());
            }

            // if xi is in s, remove it from t. Otherwise, add xi to t
            if (s.contains(list.get(i))) {
                t.remove(list.get(i));
            } else {
                t.add(list.get(i));
            }

            // if xj is in s, then with probability 0.5, remove it from t.
            // If xj is not in s, then with probability 0.5, add xj to t.
            if (s.contains(list.get(j))) {
                if (random.nextDouble() > 0.5) {
                    t.remove(list.get(j));
                }
            } else {
                if (random.nextDouble() > 0.5) {
                    t.add(list.get(j));
                }
            }

            // take the closer approximation
            Long tempMin = Math.abs(targetSum - s.stream().mapToLong(Long::longValue).sum());
            Long tempMin2 = Math.abs(targetSum - t.stream().mapToLong(Long::longValue).sum());
            if ( tempMin < tempMin2) {
                min = tempMin;
                current = s;
            } else {
                min = tempMin2;
                current = t;
            }
        }
        return min;
    }

    public static void main(String args[]) {
        HillClimbingSubsetSum hillClimbingSubsetSum = new HillClimbingSubsetSum();
        ArrayList<Long> list = new ArrayList();
        Random random = new Random();
        Double l = new Double("1000000000000");
        for(int i = 0; i < 100; i++) {
            list.add(((Double)(random.nextDouble() * l)).longValue());
        }
        System.out.println(hillClimbingSubsetSum.containsSubsetSum(list, ((Double)(25 * l)).longValue(), 1000));
    }
}
