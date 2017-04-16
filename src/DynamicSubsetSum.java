import java.util.ArrayList;
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
    @Override
    public boolean containsSubsetSum(List<Integer> list, int targetSum) {
        int n = list.size();
        int k = list.stream().mapToInt(Integer::intValue).sum();
        map = new boolean[n][k];
        return containsSum(n, k);


    }

    private boolean containsSum(int n, int k) {
//        if (n == 0) {
//            return true;
//        } else {
//            return containsSum(n - 1, k);
//        }
    }

    public static void main(String args[]) {
        DynamicSubsetSum exhaustiveSubsetSum = new DynamicSubsetSum();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            list.add(new Integer(i));
        }
        System.out.println(exhaustiveSubsetSum.containsSubsetSum(list, 15));
    }
}
