// https://leetcode.com/problems/subsets/description/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // Initialize the result list to store all subsets
        List<List<Integer>> result = new ArrayList<>();
        // Create a temporary list to store subsets during recursion
        List<Integer> subset = new ArrayList<>();
        
        // Start the recursive function to create subsets
        createSubset(nums, 0, result, subset);
        return result;
    }
    
    // Recursive function to create subsets
    private void createSubset(int[] nums, int index, List<List<Integer>> result, List<Integer> subset) {
        // If the recursion has processed all the elements of the input array
        if (index == nums.length) {
            result.add(new ArrayList<>(subset));
            return;
        }
        
        // Include the current element and move to the next index
        subset.add(nums[index]);
        createSubset(nums, index + 1, result, subset);

        // After the recursive call, remove the last element to backtrack
        subset.remove(subset.size() - 1);

        // Exclude the current element and move to the next index
        createSubset(nums, index + 1, result, subset);
    }
}

/**
 * Example with nums = [1, 2, 3]:
 *
 * Initial Call:
 * subsets([1, 2, 3])
 * - result = []
 * - subset = []
 * - Call createSubset([1, 2, 3], 0, result, subset)
 *
 * First Call to createSubset:
 * createSubset([1, 2, 3], 0, result, subset)
 * - index = 0, nums[0] = 1
 * - subset = [1]
 * - Call createSubset([1, 2, 3], 1, result, subset)
 *
 * Second Call:
 * createSubset([1, 2, 3], 1, result, subset)
 * - index = 1, nums[1] = 2
 * - subset = [1, 2]
 * - Call createSubset([1, 2, 3], 2, result, subset)
 *
 * Third Call:
 * createSubset([1, 2, 3], 2, result, subset)
 * - index = 2, nums[2] = 3
 * - subset = [1, 2, 3]
 * - Call createSubset([1, 2, 3], 3, result, subset)
 *
 * Fourth Call (Base Case):
 * createSubset([1, 2, 3], 3, result, subset)
 * - index = 3 (base case)
 * - result = [[1, 2, 3]]
 * - Return
 *
 * Back to Third Call:
 * - subset = [1, 2]
 * - Call createSubset([1, 2, 3], 3, result, subset)
 *
 * Fifth Call (Base Case):
 * createSubset([1, 2, 3], 3, result, subset)
 * - index = 3 (base case)
 * - result = [[1, 2, 3], [1, 2]]
 * - Return
 *
 * Back to Second Call:
 * - subset = [1]
 * - Call createSubset([1, 2, 3], 2, result, subset)
 *
 * Sixth Call:
 * createSubset([1, 2, 3], 2, result, subset)
 * - index = 2, nums[2] = 3
 * - subset = [1, 3]
 * - Call createSubset([1, 2, 3], 3, result, subset)
 *
 * Seventh Call (Base Case):
 * createSubset([1, 2, 3], 3, result, subset)
 * - index = 3 (base case)
 * - result = [[1, 2, 3], [1, 2], [1, 3]]
 * - Return
 *
 * Back to Sixth Call:
 * - subset = [1]
 * - Call createSubset([1, 2, 3], 3, result, subset)
 *
 * Eighth Call (Base Case):
 * createSubset([1, 2, 3], 3, result, subset)
 * - index = 3 (base case)
 * - result = [[1, 2, 3], [1, 2], [1, 3], [1]]
 * - Return
 *
 * Back to First Call:
 * - subset = []
 * - Call createSubset([1, 2, 3], 1, result, subset)
 *
 * Ninth Call:
 * createSubset([1, 2, 3], 1, result, subset)
 * - index = 1, nums[1] = 2
 * - subset = [2]
 * - Call createSubset([1, 2, 3], 2, result, subset)
 *
 * Tenth Call:
 * createSubset([1, 2, 3], 2, result, subset)
 * - index = 2, nums[2] = 3
 * - subset = [2, 3]
 * - Call createSubset([1, 2, 3], 3, result, subset)
 *
 * Eleventh Call (Base Case):
 * createSubset([1, 2, 3], 3, result, subset)
 * - index = 3 (base case)
 * - result = [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3]]
 * - Return
 *
 * Back to Tenth Call:
 * - subset = [2]
 * - Call createSubset([1, 2, 3], 3, result, subset)
 *
 * Twelfth Call (Base Case):
 * createSubset([1, 2, 3], 3, result, subset)
 * - index = 3 (base case)
 * - result = [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2]]
 * - Return
 *
 * Back to Ninth Call:
 * - subset = []
 * - Call createSubset([1, 2, 3], 2, result, subset)
 *
 * Thirteenth Call:
 * createSubset([1, 2, 3], 2, result, subset)
 * - index = 2, nums[2] = 3
 * - subset = [3]
 * - Call createSubset([1, 2, 3], 3, result, subset)
 *
 * Fourteenth Call (Base Case):
 * createSubset([1, 2, 3], 3, result, subset)
 * - index = 3 (base case)
 * - result = [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3]]
 * - Return
 *
 * Back to Thirteenth Call:
 * - subset = []
 * - Call createSubset([1, 2, 3], 3, result, subset)
 *
 * Fifteenth Call (Base Case):
 * createSubset([1, 2, 3], 3, result, subset)
 * - index = 3 (base case)
 * - result = [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]
 * - Return
 *
 * Final Result:
 * - result = [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]
 */
