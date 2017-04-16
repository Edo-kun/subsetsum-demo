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

/** Class that demonstrates exhaustive search to find subset sum of a multiset*/
public class ExhaustiveSubsetSum implements SubsetSum{
    /**
     * Returns true if a subset of list amounts to the target sum
     * @param list the list of integers
     * @param targetSum the target sum of the subsets
     * @return
     */
    @Override
    public boolean containsSubsetSum(List<Integer> list, int targetSum) {
        // get 2^n sublists
        ArrayList<ArrayList<Integer>> sublists = getSublists(list, list.size()-1);
        // calculate sums of each sublist
        for (ArrayList<Integer> sublist : sublists) {
            int sum = 0;
            for (Integer num : sublist) {
                sum += num.intValue();
            }
            if (sum == targetSum) {
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
        // if i == 0 -> return {},{list.get(0)}
        // else i > 0 -> return getSublists(list, i - 1), (setSublists(list, i -1) + list.get(i) for every sublist)
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
        for(int i = 1; i < 11; i++) {
            list.add(new Integer(i));
        }
        System.out.println(exhaustiveSubsetSum.containsSubsetSum(list, 15));
    }
}