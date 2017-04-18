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
        // get max 2^n sums
        ArrayList<Integer> sums = getSums(list, list.size()-1, targetSum);
        // calculate sums of each sublist
        for (Integer sum : sums) {
            if (sum.intValue() == targetSum) {
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
    private ArrayList<Integer> getSums(List<Integer> list, int index, int target) {
        // if i == 0 -> return {},{list.get(0)}
        // else i > 0 -> return getSums(list, i - 1), (getSums(list, i -1) + list.get(i) for every sum)
        ArrayList<Integer> subSums = new ArrayList();
        // base case
        if(index <= 0 ) {
            subSums.add(0);
            subSums.add(new Integer(list.get(0)));
        } else {
            // calculate subSums of n-1,
            // make a copy and add the integer to every subSum of the copy,
            // add every subSum in the copy back to the original subSum list
            subSums = getSums(list, index-1, target);
            ArrayList<Integer> copy = getSums(list, index-1, target);
            for (int i = 0; i < copy.size(); i++) {
                int sum = copy.get(i) + list.get(index);
                //only add to subsums if sum does not exceed target (pruning)
                if (sum <= target) {
                    subSums.add(copy.get(i) + list.get(index));
                }
            }
        }
        return subSums;
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