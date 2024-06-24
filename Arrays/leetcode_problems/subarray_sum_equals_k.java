// https://leetcode.com/problems/subarray-sum-equals-k/

import java.util.HashMap;

// Time: O(n)
// Space: O(n) because of the HashMap
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;  // Cumulative sum up to i
        // Use a HashMap to store the counts of different prefix sums encountered in the array
        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();
        // Initialize HashMap with prefix sum 0 having count 1 to handle cases where subarray sum itself is k
        prefixSumCount.put(0, 1);

        for (int num : nums) {
            prefixSum += num;
            if (prefixSumCount.containsKey(prefixSum - k)) {
                // If we have a prefix sum where the current prefix sum - k == this sum
                // this means we found a subarray that sums to k
                count += prefixSumCount.get(prefixSum - k);
            }
            // Update the count of the current prefix sum in the HashMap
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}