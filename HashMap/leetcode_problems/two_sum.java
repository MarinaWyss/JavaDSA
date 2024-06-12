// https://leetcode.com/problems/two-sum/description/

/* The brute force approach here is to use nested lists to check each 
 * combination of elements, but this is O(n^2). 
 * Instead, this approach is O(n).
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        // Iterate through the array from left to right
        // Check if target - nums[i] exists in the set
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // If so, we have found a match and can return it
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            // If not, we add the element and its index to the map
            // We do this here to satisfy the requirement that we don't use
            // The same element twice (because complements must come from previously-
            // encountered elements)
            map.put(nums[i], i);
        }
        // No solution found
        return new int[]{};
    }
}