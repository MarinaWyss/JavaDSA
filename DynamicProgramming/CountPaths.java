// Class to demonstrate different approaches for 2D DP

public class CountPaths {

    // Brute force using DFS and recusion
    // Time: O(2 ^ (n + m)), Space: O(n + m)
    public static int bruteForce(int row, int col, int numRows, int numCols) {
    
        // Check if we go out of bounds
        // Note we are only moving down and right so we don't have to check
        // every direction
        if (row >= numRows || col >= numCols) return 0;
    
        // Check if we reach the bottom right
        if (row == numRows - 1 && col == numCols - 1) return 1;
    
        // Move down and right
        return bruteForce(row + 1, col, numRows, numCols) + bruteForce(row, col + 1, numRows, numCols);
    }

    // Top-down approach
    // Time and Space: O(n * m)
    public static int memoization(int row, int col, int numRows, int numCols, int[][] cache) {

        // Check if we go out of bounds
        if (row >= numRows || col >= numCols) return 0;

        // If the result is already in the cache, return it
        // Note this assumes cache was initialized with all zeros
        if (cache[row][col] > 0) return cache[row][col];

        // Check if we reach the bottom right
        if (row == numRows - 1 && col == numCols - 1) return 1;

        // Move down and right
        cache[row][col] = memoization(row + 1, col, numRows, numCols, cache) + 
            memoization(row, col + 1, numRows, numCols, cache);

        return cache[row][col];
    }

    // Bottom-up Dynamic Programming. In this case we don't need to have a cache so can save space
    // Time: O(n * m), Space: O(m), where m is num of cols
    public static int dynamicProgramming(int numRows, int numCols) {

        // Create a row of all zeros represeting the row below the real bottom row
        int[] prevRow = new int[numCols];

        // Start at second-to-last column because we know the last column will always be 1
        // Move from the bottom up
        for (int i = numRows - 1; i >= 0; i--) {
            // Create a new row of all zeros and a 1 at the end
            int[] curRow = new int[numCols];
            curRow[numCols - 1] = 1;

            // For each col in the row, move from right to left
            for (int j = numCols - 2; j >= 0; j--) {
                // Get value to the right and below
                curRow[j] = curRow[j + 1] + prevRow[j];
            }
            // Store calculations for next iteration
            prevRow = curRow;
        }
        return prevRow[0];
    }
}
