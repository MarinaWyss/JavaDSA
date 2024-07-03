// https://leetcode.com/problems/subsets-ii/description/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // Sort array so duplicates are next to each other
        Arrays.sort(nums);
        // Initialize the result list to store all subsets
        List<List<Integer>> result = new ArrayList<>();
        // Create a temporary list to store subsets during recursion
        List<Integer> subset = new ArrayList<>();
        // subsetsWithDup the recursive function to create subsets
        createSubset(nums, 0, result, subset);
        return result;
    }

    // Recursive function to create subsets
    private void createSubset(int[] nums, int i, List<List<Integer>> result, List<Integer> subset) {
        // If the recursion has processed all the elements of the input array
        if (i == nums.length) {
            result.add(new ArrayList<>(subset));
            return;
        }
        
        // Include the current element and move to the next index
        subset.add(nums[i]);
        createSubset(nums, i + 1, result, subset);

        // After the recursive call, remove the last element to backtrack
        subset.remove(subset.size() - 1);

        // Skip over any duplicates
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }

        // Exclude the current element and move to the next index
        createSubset(nums, i + 1, result, subset);
    }
}
