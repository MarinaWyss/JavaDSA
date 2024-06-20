// https://leetcode.com/problems/minimum-size-subarray-sum/description/

public class Solution {

    // Lots of nested loops, so Time: O(n^3)
    // Space: O(1)
    public int bruteForce(int[] nums, int target) {
        // Initialize minLength to the largest possible integer value
        // If minLength is initialized to 0, it would always be the minimum value, 
        // and the correct subarray length would never be properly calculated or returned.
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int sum = 0;
                // Calculate sum for the current subset
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                    if (sum >= target) {
                        // j - i is the end index - beginning, and we add 1 to be inclusive of both elements
                        minLength = Math.min(minLength, j - i + 1);
                    }
                }
            }
        }
        // If minLength was not updated, return 0 indicating no valid subarray was found
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // Time: O(n) (not O(n^2 because the inner loop only runs once))
    // Space: O(1)
    public int shortestSubarray(int[] nums, int target) {
        int L = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE; // Could also use length of input array + 1

        for (int R = 0; R < nums.length; R++) {
            // As we shift the right pointer, add it to the windowSum
            windowSum += nums[R];
            // If the windowSum is ever greater than target we have a valid window
            while (windowSum >= target) {
                // So we check if the length of the valid window is smaller than the current min
                minLength = Math.min(R - L + 1, minLength);
                // We then try to shrink the window by taking the left value and removing it from the total
                windowSum -= nums[L];
                // Move the left pointer to the right
                L++;
                // We continue to remove left values until we no longer have a valid subarray
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }




}
