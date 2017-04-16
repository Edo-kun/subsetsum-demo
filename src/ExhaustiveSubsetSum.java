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

/** Class that demonstrates exhaustive search to find subset sum of a multiset*/
public class ExhaustiveSubsetSum {
    /**
     * Returns true if a subset of list amounts to the target sum
     * @param list the list of integers
     * @param targetSum the target sum of the subsets
     * @return
     */
    private boolean containsSubsetSum(List<Integer> list, int targetSum) {
        // get sublists
        ArrayList<ArrayList<Integer>> sublists = getSublists(list, list.size()-1);
        // calculate sums of each sublist
        for (ArrayList<Integer> sublist : sublists) {
            int sum = 0;
            for (Integer num : sublist) {
                sum += num.intValue();
            }
            if (sum == targetSum) {
                System.out.println(sublist);
                return true;
            }
        }
        return false;
    }

    /**
     * Recursively find subsets of a given list
     * @param list the list
     * @param index the index of the int to add to the clone of the list
     * @return
     */
    private ArrayList<ArrayList<Integer>> getSublists(List<Integer> list, int index) {
        ArrayList<ArrayList<Integer>> subsets = new ArrayList();
        // base case
        if(index == 0 ) {
            subsets.add(new ArrayList());
            ArrayList<Integer> base = new ArrayList<>();
            base.add(new Integer(list.get(0)));
            subsets.add(base);
        } else {
            // calculate sublists of n-1,
            // make a copy and add the integer at index of list to every sublist of the copy,
            // add every sublist in the copy back to the original sublist
            subsets = getSublists(list, index-1);
            ArrayList<ArrayList<Integer>> copy = getSublists(list, index-1);
            for(ArrayList<Integer> sub : copy) {
                sub.add(list.get(index));
                subsets.add(sub);
            }
        }
        return subsets;
    }

    public static void main(String args[]) {
        ExhaustiveSubsetSum exhaustiveSubsetSum = new ExhaustiveSubsetSum();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            list.add(new Integer(i));
        }
        System.out.println(exhaustiveSubsetSum.containsSubsetSum(list, 15));
    }
}
