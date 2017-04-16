import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class DynamicSubsetSum implements SubsetSum {
    private boolean[][] map;

    public boolean containsSubsetSum(List<Integer> list, int targetSum) {
        if (targetSum == 0) {
            return true;
        }
        Collections.sort(list);
        int n = list.size();
        int k = list.stream().mapToInt(Integer::intValue).sum();
        map = new boolean[n][k];

        for (int i = 0; i < k; i++) {
            map[0][i] = list.get(0) == i;
        }

        for (int s = 0; s < k; s++) {
            for (int i = 1; i < n; i++) {
                if (s-list.get(i) > 0){
                    map[i][s] = map[i-1][s] || list.get(i) == s || map[i-1][s-list.get(i)];
                } else {
                    map[i][s] = map[i-1][s] || list.get(i) == s;
                }
            }
        }

        try {
            return map[n-1][targetSum-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static void main(String args[]) {
        DynamicSubsetSum dynamicSubsetSum = new DynamicSubsetSum();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i < 100; i++) {
            list.add(new Integer(i));
        }
        System.out.println(dynamicSubsetSum.containsSubsetSum(list, 99));
    }
}
