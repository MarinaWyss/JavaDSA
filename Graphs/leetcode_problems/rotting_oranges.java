// https://leetcode.com/problems/rotting-oranges/description/

import java.util.Queue;
import java.util.LinkedList;

/*
 * Use BFS to simulate the spread of rot minute-by-minute, ensuring
 * all adjacent oranges are processed and become rotten at the same time.
 * This ensures we caculate the minimum time required for all 
 * fresh oranges to become rotten, if possible.
 */


 class Solution {
    public int orangesRotting(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        
        // Create a queue to keep track of the coordinates of all initially rotten oranges.
        Queue<int[]> queue = new LinkedList<>();
        // Store the count of fresh oranges.
        int numFresh = 0;

        // First, scan grid to enqueue rotting oranges and count fresh
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (grid[r][c] == 1) {
                    numFresh++;
                }
                else if (grid[r][c] == 2) {
                    // If we find a rotting orange, enqueue its coords
                    queue.add(new int[]{r, c});
                }
            }
        }
        
        // If there are no fresh oranges, return 0
        if (numFresh == 0) {
            return 0;
        }
        // If there are no rotting oranges and there are fresh oranges, return -1
        if (queue.isEmpty()) {
            return -1;
        }

        // Perform BFS on the grid using the initially enqueued rotten oranges.
        int numMins = 0;
        int[][] dirs = {{1, 0},{-1, 0},{0, -1},{0, 1}};
        while (!queue.isEmpty()) {
            int queueSize = queue.size();  // Since queues change, we want this to be defined outside the loop
            boolean rottenThisMinute = false;

            for (int i = 0; i < queueSize; i++) {
                int pair[] = queue.poll();
                int row = pair[0], col = pair[1];
        
                for (int[] dir : dirs) {
                    // We could define coordinates relative to the row/col on each iteration
                    // Or just add the directions to the coordinates
                    int newRow = row + dir[0], newCol = col + dir[1];
                    // Make sure we aren't out of bounds
                    if (newRow >= 0 && newRow < numRows && 
                        newCol >= 0 && newCol < numCols &&
                        // and check if we reach a fresh orange
                        grid[newRow][newCol] == 1) {
                            // If so, the orange is rotten
                            grid[newRow][newCol] = 2;
                            numFresh--;
                            // Add newly-rotten organge to queue to process next time
                            queue.add(new int[] {newRow, newCol});
                            rottenThisMinute = true;
                    }
                }
            }
            if (rottenThisMinute) {
                numMins++;
            };
        }
        return numFresh == 0 ? numMins : -1;
    }
}