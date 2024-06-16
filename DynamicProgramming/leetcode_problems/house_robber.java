// https://leetcode.com/problems/house-robber/description/

// O(n) time and space complexity

class Solution {
    public int rob(int[] nums) {
        int numHouses = nums.length;

        if (numHouses == 1) return nums[0];

        // Initialize a dynamic programming array of size numHouses with zeros
        // This will store the max amount of money that can be robbed up to the 
        // i-th house
        int[] dp = new int[numHouses];

        // Initialize base cases: Only rob first house, or first or second
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        /* Iterate from third to last house
        At each step, the robber has two choices:
            1. Rob the i-th house:
                If the robber chooses to rob the i-th house, they 
                cannot rob the (i-1)-th house. So the total amount they can 
                rob up to the i-th house is the money in the i-th house plus 
                the maximum amount robbed up to the (i-2)-th house: nums[i] + dp[i-2].
            2. Skip the i-th house:
                If the robber skips the i-th house, the total amount they can 
                rob up to the i-th house is the same as the maximum amount robbed 
                up to the (i-1)-th house: dp[i-1].
            
        The maximum amount that can be robbed up to the i-th house is 
        therefore the max of these two choices. */
        for (int i = 2; i < numHouses; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }

        // The max amount that can be robbed is stored in the last element of dp
        return dp[numHouses-1]; 
    }
}