// https://leetcode.com/problems/maximum-subarray/description/

class Solution {  // Using Kadane's
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int curSum = 0;

        for (int n : nums) {
            curSum = Math.max(curSum, 0);
            curSum += n;
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }
}
