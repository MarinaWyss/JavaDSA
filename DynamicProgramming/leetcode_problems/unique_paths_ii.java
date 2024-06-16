// https://leetcode.com/problems/unique-paths-ii/description/


// Time & Space: O(rows * cols)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // If the starting point or the ending point is an obstacle, return 0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        // Create a DP array to store the number of ways to reach each cell
        int[][] dp = new int[m][n];

        // Initialize the starting point
        dp[0][0] = 1;

        // Fill the first column
        for (int i = 1; i < m; i++) {
            // If there's an obstacle, set dp[i][0] to 0
            // otherwise carry forward the value from above
            dp[i][0] = (obstacleGrid[i][0] == 1) ? 0 : dp[i - 1][0];
        }

        // Fill the first row
        for (int j = 1; j < n; j++) {
            // If there's an obstacle, set dp[0][j] to 0
            // otherwise carry forward the value from the left
            dp[0][j] = (obstacleGrid[0][j] == 1) ? 0 : dp[0][j - 1];
        }

        // Fill the rest of the DP array
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // If there's an obstacle, set dp[i][j] to 0
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    // Sum the ways from the top and left cells if they are not obstacles
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        // Return the number of ways to reach the bottom-right corner
        return dp[m - 1][n - 1];
    }
}
