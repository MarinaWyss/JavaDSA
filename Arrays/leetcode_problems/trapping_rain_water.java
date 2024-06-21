// https://leetcode.com/problems/trapping-rain-water/description/

class Solution {

    // Since both inner loops are nested within the outer loop, 
    // the total time complexity is O(n^2)
    // Space: O(1)
    public int trapBruteForce(int[] height) {
        int water = 0;

        // Loop from second to second-to-last elements so there are always
        // bars on the left and right
        for (int i = 1; i < height.length - 1; i++) {

            // Find the maximum height to the left of the current element
            int maxLeft = 0;
            for (int l = 0; l <= i; l++) {
                maxLeft = Math.max(maxLeft, height[l]);
            }

            // Find the maximum height to the right of the current element
            int maxRight = 0;
            for (int r = i; r < height.length; r++) {
                maxRight = Math.max(maxRight, height[r]);
            }

            water += Math.min(maxLeft, maxRight) - height[i];
        }
        return water;
    }

    // Time: O(n), Space: O(1)
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int L = 0, R = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (L <= R) {
            // If the height at the left pointer is less than or equal to the height at the right pointer
            if (height[L] <= height[R]) {
                // If the current height at the left pointer is greater than or equal to leftMax, update leftMax
                if (height[L] >= leftMax) {
                    leftMax = height[L];
                } else {
                    // Add the difference between leftMax and the current height at the left pointer to water
                    water += leftMax - height[L];
                }
                // Move the left pointer one step to the right
                L++;
            } else {
                // If the height at the right pointer is less than the height at the left pointer
                // If the current height at the right pointer is greater than or equal to rightMax, update rightMax
                if (height[R] >= rightMax) {
                    rightMax = height[R];
                } else {
                    // Add the difference between rightMax and the current height at the right pointer to water
                    water += rightMax - height[R];
                }
                // Move the right pointer one step to the left
                R--;
            }
        }
        return water;
    }
}