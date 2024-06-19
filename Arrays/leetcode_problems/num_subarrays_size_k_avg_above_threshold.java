// https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/description/

/* Easy to understand brute-force version with O(n * k) time complexity
class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // Just calculate the average for each window
        int res = 0;
        for (int L = 0; L <= arr.length - k; L++) {
            int sum = 0;
            for (int R = L; R < L + k; R++) {
                sum += arr[R];
            }
            if (sum / k >= threshold) {
                res++;
            }
        }
        return res;
    }
}
*/

// More efficient approach
class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int res = 0;  
        // Sum that a sub-array of size k needs to meet or exceed in order for its 
        // average to be greater than or equal to threshold.
        int targetSum = threshold * k;
        int currentSum = 0;  // Sum of current subarray

        // Calculate the sum of the first window of size k
        // This is our starting sum we will update for the rest of the window
        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }

        // Check if the average of the first window is greater than or equal to threshold
        if (currentSum >= targetSum) {
            res++;
        }

        // Slide the window over the rest of the array
        for (int i = k; i < arr.length; i++) {
            currentSum -= arr[i - k];  // Remove value to the left that we're sliding past
            currentSum += arr[i];  // Add current value we're sliding to
            if (currentSum >= targetSum) {
                res++;
            }
        }

        return res;
    }
}