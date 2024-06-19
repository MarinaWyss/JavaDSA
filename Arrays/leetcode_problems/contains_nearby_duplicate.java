// https://leetcode.com/problems/contains-duplicate-ii/

import java.util.HashMap;

// O(n)
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // If we've seen an element before, get its last index
            if (map.containsKey(nums[i])) {
                int j = map.get(nums[i]);
                // Check if the abs(i - j) <= k condition is satisfied
                if (Math.abs(i - j) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}