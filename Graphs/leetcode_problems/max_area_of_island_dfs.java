// https://leetcode.com/problems/max-area-of-island/

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int currentIslandSize = 0;
        int maxIslandSize = 0;
        int numRows = grid.length;
        int numCols = grid[0].length;

        // Iterate over each cell in the grid to see if it's part of an island
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] == 1) {
                    // If we find an island, figure out how big it is using DFS
                    currentIslandSize = dfs(grid, row, col, numRows, numCols);
                }
                if (currentIslandSize > maxIslandSize) {
                    maxIslandSize = currentIslandSize;
                }
            }
        }
        return maxIslandSize;
    }

    public int dfs(int[][] grid, int row, int col, int numRows, int numCols) {
        // Base case: We go out of bounds or hit water (0)
        if (row >= numRows || col >= numCols || row < 0 || col < 0 || grid[row][col] == 0) {
            return 0;
        }

        int currentIslandSize = 1;
        // Mark the cell visited by changing it to 0
        grid[row][col] = 0;
        // Explore neighboring cells in all four directions
        currentIslandSize += dfs(grid, row + 1, col, numRows, numCols); // down
        currentIslandSize += dfs(grid, row - 1, col, numRows, numCols); // up
        currentIslandSize += dfs(grid, row, col + 1, numRows, numCols); // right
        currentIslandSize += dfs(grid, row, col - 1, numRows, numCols); // left

        return currentIslandSize;
    }
}