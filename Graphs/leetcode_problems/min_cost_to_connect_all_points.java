// https://leetcode.com/problems/min-cost-to-connect-all-points/description/

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashSet;

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        // Min-heap to store edges by weight (Manhattan distance)
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // To keep track of visited points
        HashSet<Integer> visited = new HashSet<>();
        // Total cost to connect all points
        int totalCost = 0;

        // Start from the first point
        visited.add(0);
        // Add all edges from the first point to the heap
        for (int i = 1; i < n; i++) {
            int weight = manhattanDistance(points[0], points[i]);
            minHeap.add(new int[] {weight, 0, i});
        }

        // Continue until all points are connected
        while (visited.size() < n) {
            // Get the edge with the smallest weight
            int[] cur = minHeap.poll();
            int weight = cur[0];
            int point2 = cur[2];

            // If the point is already visited, skip it
            if (visited.contains(point2)) {
                continue;
            }

            // Add the edge to the total cost
            totalCost += weight;
            // Mark the point as visited
            visited.add(point2);

            // Add all edges from the newly visited point to the heap
            for (int i = 0; i < n; i++) {
                if (!visited.contains(i)) {
                    int newWeight = manhattanDistance(points[point2], points[i]);
                    minHeap.add(new int[] {newWeight, point2, i});
                }
            }
        }

        return totalCost;
    }

    private static int manhattanDistance(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
}