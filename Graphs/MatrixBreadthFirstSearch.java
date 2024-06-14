import java.util.Deque;
import java.util.ArrayDeque;

/* Find the shortest path from top left to bottom right.

Make sure to confirm if a path is guaranteed to exist.
 */

public class MatrixBreadthFirstSearch {

    public int breadthFirstSearch(int[][] grid) {
        int rowDim = grid.length;
        int colDim = grid[0].length;

        // Initialize a way to manage which nodes have already been visited
        int[][] visited = new int[rowDim][colDim];

        // Use a queue to manage the nodes to be explored
        Deque<int[]> queue = new ArrayDeque<>();

        // Enqueue starting coordinates and mark it as visited
        queue.add(new int[]{0, 0});
        visited[0][0] = 1;

        int length = 1; // Length of the shortest path.
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int pair[] = queue.poll();
                int row = pair[0], col = pair[1];
                // If we have reached the bottom right, return the length of the path
                if (row == rowDim - 1 && col == colDim - 1) {
                    return length;
                }
                
                // Figure out the four neighbors from this location
                int[][] neighbors = {{row, col + 1}, {row, col - 1}, {row + 1, col}, {row - 1, col}};
                for (int[] neighbor : neighbors) {
                    int newRow = neighbor[0], newCol = neighbor[1];
                    // If we have gone past the edge of the rows or columns
                    if (Math.min(newRow, newCol) < 0 ||
                    // Or, the rows and cols could also be too large
                        // We don't need to check >= because rowDim is the number of rows, not the index
                        // Have to check rows and cols separately (not max) 
                        // because we might have a different number of rows and cols
                        newRow == rowDim || newCol == colDim ||
                        // Or, we reach a position we have already visited 
                        visited[newRow][newCol] == 1 || 
                        // Or, aren't allowed to visit
                        grid[newRow][newCol] == 1) {
                            // Continue to the next iteration
                            continue;
                    }
                    queue.add(new int[]{newRow, newCol});
                    visited[newRow][newCol] = 1;
                }
            }
            length++;
        }
        return -1; // Return -1 if no path exists
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0},
            {1, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 1, 1, 0}
        };

        MatrixBreadthFirstSearch solver = new MatrixBreadthFirstSearch();
        int shortestPath = solver.breadthFirstSearch(grid);

        System.out.println("Shortest path: " + shortestPath);
    }
}

