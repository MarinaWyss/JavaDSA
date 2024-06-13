// https://leetcode.com/problems/number-of-islands/

/*
 * Using DFS since it is easy to implement with recursion.
 * We would want to use BFS if we had a very large grid which could lead
 * to a potential stack overflow.
 */

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;
        int numRows = grid.length;
        int numCols = grid[0].length;

        // Iterate over each cell in the grid to see if it is part of an island
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] == '1') {
                    numIslands++;
                    // When we find a new part of an island, run DFS
                    dfs(grid, row, col, numRows, numCols);
                }
            }
        }
        return numIslands;
    }

    public void dfs(char[][] grid, int row, int col, int numRows, int numCols) {
        // Base case: If we go out of bounds or hit water ('0')
        if (row >= numRows || col >= numCols || row < 0 || col < 0 || grid[row][col] == '0') {
            return;
        }

        // Mark the cell visited by changing it to '0'
        // If we weren't allowed to change the grid we could also use a visited matrix
        grid[row][col] = '0';

        // Explore neighboring cells in all directions
        dfs(grid, row + 1, col, numRows, numCols);
        dfs(grid, row - 1, col, numRows, numCols);
        dfs(grid, row, col + 1, numRows, numCols);
        dfs(grid, row, col - 1, numRows, numCols);
    }
}

/*
Example:

Initial grid:
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1

Step-by-step execution:

1. First Island:
   - Start at (0,0).
   - Perform DFS to mark all connected '1's as '0':
     0 0 0 0 0
     0 0 0 0 0
     0 0 1 0 0
     0 0 0 1 1
   - numIslands = 1.

2. Continue searching:
   - Skip cells (0,1), (1,0), and (1,1) since they are '0'.

3. Second Island:
   - Find a '1' at (2,2) and start DFS:
     0 0 0 0 0
     0 0 0 0 0
     0 0 0 0 0
     0 0 0 1 1
   - numIslands = 2.

4. Continue searching:
   - Skip cells (2,0) to (2,4) since they are '0'.

5. Third Island:
   - Find a '1' at (3,3) and start DFS:
     0 0 0 0 0
     0 0 0 0 0
     0 0 0 0 0
     0 0 0 0 0
   - numIslands = 3.

6. Complete the search:
   - All cells are checked, and the final count is 3.

Final output: Number of islands: 3.
*/