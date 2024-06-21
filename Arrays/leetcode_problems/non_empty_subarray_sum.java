public class Kadane {

    // Q: Find a non-empty subarray with the largest sum. Return the sum.

    // Brute force: O(n^2)
    public static int bruteForce(int[] nums) {
        int maxSum = nums[0];  // default

        // Outer loop to set the starting point of the subarray
        for (int i = 0; i < nums.length; i++) {
            // currSum is the sum starting from index i to j
            int currSum = 0;
            // Inner loop to extend the subarray to include elements from i to j
            for (int j = 0; j < nums.length; j++) {
                curSum += nums[j];   // Add the current element to curSum
                maxSum = Math.max(maxSum, curSum);
            }
        }
        return maxSum;
    }

    // Kadane's Algorithm: O(n)
    public static int kadanes(int[] nums) {
        int maxSum = nums[0];
        int currSum = 0;  // sum of the current subarray

        for (int n : nums) {
            // If currSum is negative, reset it to 0 before adding the current element
            currSum = Math.max(currSum, 0);
            // Add the current element to currSum
            currSum += n;
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    // Q: Find a non-empty subarray with the largest sum. Return the start and end indices.

    // Sliding Window Kadane's: O(n)
    public int[] slidingWindow(int[] nums) {
        int maxSum = nums[0];
        int currSum = 0;
        // Initialize indices for the maximum subarray and current subarray
        int maxL = 0, maxR = 0, currL = 0;

        // Loop through each element in the array using currR as the right boundary of the window
        for (int currR = 0; currR < nums.length; currR++) {
            // If currSum is negative, reset it to 0 and set currL to the current index
            if (currSum < 0) {
                currSum = 0;
                currL = currR;
            }

            currSum += nums[currR];
            if (currSum > maxSum) {
                maxSum = currSum;
                maxL = currL; 
                maxR = currR;     
            }    
        }    
        return new int[] {maxL, maxR};
    }
}