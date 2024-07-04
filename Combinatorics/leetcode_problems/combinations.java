// https://leetcode.com/problems/combinations/description/

import java.util.List;
import java.util.ArrayList;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combos = new ArrayList<>();
        helper(1, new ArrayList<>(), combos, n, k);
        return combos;
    }

    public static void helper(int start, List<Integer> curCombo, List<List<Integer>> combos, int n, int k) {
        // If the current combination is of length k, add it to the results
        if (curCombo.size() == k) {
            combos.add(new ArrayList<>(curCombo));
            return;
        }

        // The loop starts from 'start' and goes up to 'n - (k - curCombo.size()) + 1'
        // This ensures we have enough elements left to complete a combination of length k
        for (int i = start; i <= n - (k - curCombo.size()) + 1; i++) {
            // Add current element to the combination
            curCombo.add(i);
            // Recursively build the combination from the next element
            helper(i + 1, curCombo, combos, n, k);
            // Remove the last element to backtrack and try the next possibility
            curCombo.remove(curCombo.size() - 1);
        }
    }
}
