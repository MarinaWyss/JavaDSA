// https://leetcode.com/problems/combination-sum/description/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Result list to store matching combinations
        List<List<Integer>> result = new ArrayList<>();
        // List to temporarily store combinations
        List<Integer> combination = new ArrayList<>();

        // Recursive function to create combinations
        backtrack(candidates, target, combination, result, 0);
        return result;
    }

    // Use backtracking to explore all possible combinations
    private void backtrack(int[] candidates, int target, List<Integer> combination, List<List<Integer>> result, int start) {
        // If the target is negative there is no valid combo and we need to backtrack
        if (target < 0) {
            return;
        }

        // If the target is 0, a valid combination has been found
        if (target == 0) {
            result.add(new ArrayList<>(combination));
        }

        // Iterate through each candidate
        for (int i = start; i < candidates.length; i++) {
            // Add current candidate to the combination
            combination.add(candidates[i]);
            // Recursively call backtracking function with reduced target and current candidate
            backtrack(candidates, target - candidates[i], combination, result, i);
            // Remove the last candidate added to backtrack and try next candidate.
            combination.remove(combination.size() - 1);
        }
    }
}