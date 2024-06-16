// Class to demonstrate different approaches for 1D DP

public class Fibonacci {
    
    // Brute force
    public static int bruteForce(int n) {
        if (n <= 1) return n;

        return bruteForce(n - 1) + bruteForce(n - 2);
    }

    // Memoization
    public static int memoization(int n, int[] cache) {
        if (n <= 1) return n;

        if (cache[n] != 0) return cache[n];

        cache[n] = memoization(n - 1, cache) + memoization(n - 2, cache);
        return cache[n];
    }

    // Dynamic Programming
    public static int dynamicProgramming(int n) {
        if (n <= 1) return n;

        // Create an array to store the first two values
        int[] dp = {0, 1};
        // Start iterating from the third value
        int i = 2;
        while (i <= n) {
            // Store the last value in dp
            int tmp = dp[1];
            // Assign the last value in dp to be the sum of the previous two
            dp[1] = dp[0] + dp[1];
            // Move the previous-last value to the new first value
            // Basically, we shifted the old last value to the left
            dp[0] = tmp;
            i++;
        }
        return dp[1];
    }

}