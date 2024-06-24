// https://leetcode.com/problems/find-pivot-index/description/

// Time: O(n) (both loops run sequentially)
// Space: O(1)
class Solution {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        } 

        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // rightSum = totalSum - leftSum - current element
            // So, with this line we check if leftSum == rightSum
            if (leftSum == totalSum - leftSum - nums[i]) {
                // If true, return the current index as the pivot index
                return i;
            }
            // Update leftSum by adding the current element
            leftSum += nums[i];
        }
        return -1;
    }
}
