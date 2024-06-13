import java.lang.Math;
import java.util.Arrays;

/* Count the unique paths from the top left to the bottom right.
 * A sinlge path may only move along zeros and can't visit
 * the same cell more than once.
 * 
 * - Should clarify if there is always at least one path.
 */

public class MatrixDepthFirstSearch {

    // Count paths using backtracking
    public int depthFirstSearch(int[][] grid, int row, int col, int[][] visited) {
        int rowDim = grid.length;
        int colDim = grid[0].length;

        // If we have gone past the edge of the rows or columns
        if (Math.min(row, col) < 0 ||
            // Or, the rows and cols could also be too large
                // We don't need to check >= because rowDim is the number of rows, not the index
                // Have to check rows and cols separately (not max) 
                // because we might have a different number of rows and cols
            row == rowDim || col == colDim ||
            // Or, we reach a position we have already visited 
            visited[row][col] == 1 || 
            // Or, aren't allowed to visit
            grid[row][col] == 1) {
                // Then we've hit a base case without finding a path
                return 0;
        }

        // If we reach the last row or the last column
        if (row == rowDim - 1 && col == colDim - 1) {
            // We did find a valid path
            return 1;
        }

        // Add this coordinate to the visited set so we don't revisit cells within the 
        // same path (which would lead to infinite loops or incorrect path counts) 
        visited[row][col] = 1;

        // Explore all four directions from this location
        int count = 0;
        count += depthFirstSearch(grid, row + 1, col, visited);
        count += depthFirstSearch(grid, row - 1, col, visited);
        count += depthFirstSearch(grid, row, col + 1, visited);
        count += depthFirstSearch(grid, row, col - 1, visited);

        // Once we have explored all possible paths from the current cell, we need to backtrack. 
        // This involves marking the current cell as unvisited so that it can be part of a 
        // different path originating from the last cell. 
        // This allows the algorithm to correctly count all unique paths.
        visited[row][col] = 0;
        return count;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0},
            {1, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 1, 1, 0}
        };

        int rowDim = grid.length;
        int colDim = grid[0].length;
        int[][] visited = new int[rowDim][colDim];

        for (int[] row : visited) {
            Arrays.fill(row, 0);
        }

        MatrixDepthFirstSearch solver = new MatrixDepthFirstSearch();
        int pathCount = solver.depthFirstSearch(grid, 0, 0, visited);

        // Expect two unique paths
        System.out.println("Number of unique paths: " + pathCount);
    }
}