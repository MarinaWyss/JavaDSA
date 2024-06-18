// https://leetcode.com/problems/maximum-sum-circular-subarray/description/

/* Intuition: If the max subarray wraps around, it will be easier to calculate 
 * the negative space that is NOT the max sub-array (i.e., the min sub-array).
 * Then we can subtract the minimum sub-array from the total to get the max.
 * Time complexity: O(n)
 * Space complexity: O(1)
*/

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = nums[0]; // Maximum subarray sum in a non-circular array
        int minSum = nums[0]; // Minimum subarray sum in a non-circular array
        int curMax = 0; // Current maximum sum ending at the current position
        int curMin = 0; // Current minimum sum ending at the current position
        int totalSum = 0; // Total sum of the array
        int res = 0;

        // Iterate through each element in the array
        for (int n : nums) {
            // Calculate the current maximum sum ending at the current position
            curMax = Math.max(curMax + n, n);
            // Update the maximum subarray sum if the current maximum is greater
            maxSum = Math.max(maxSum, curMax);

            // Calculate the current minimum sum ending at the current position
            curMin = Math.min(curMin + n, n);
            // Update the minimum subarray sum if the current minimum is smaller
            minSum = Math.min(minSum, curMin);

            // Add the current element to the total sum of the array
            totalSum += n;
        }

        // If the maximum sum is greater than 0, it means the array has at least one positive number
        if (maxSum > 0) {
            // The maximum subarray sum for a circular array is the maximum of the non-circular max sum
            // and the circular sum (total sum minus the minimum subarray sum)
            res = Math.max(maxSum, totalSum - minSum);
        } else {
            // If all elements are negative, the maximum sum is the maximum subarray sum found by Kadane's algorithm
            res = maxSum;
        }

        return res;
    }
}


