// https://leetcode.com/problems/network-delay-time/description/

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Create an adjacency list to store the graph
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adj.put(i, new ArrayList<>());
        }
        
        // Fill the adjacency list with edges and their travel times
        for (int[] time : times) {
            int u = time[0]; // Source node
            int v = time[1]; // Target node
            int w = time[2]; // Travel time
            adj.get(u).add(new int[] {v, w});
        }

        // Create a map to store the shortest travel time to each node
        Map<Integer, Integer> minTime = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            minTime.put(i, Integer.MAX_VALUE);
        }

        // Priority queue (min-heap) to order nodes by their travel times
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.add(new int[] {k, 0});
        minTime.put(k, 0);

        // Process the nodes in the priority queue
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int node = cur[0]; // Current node
            int time = cur[1]; // Current travel time

            // Process each adjacent node of the current node
            for (int[] neighbor : adj.get(node)) {
                int nextNode = neighbor[0]; // Adjacent node
                int travelTime = neighbor[1]; // Travel time to the adjacent node
                int newTime = time + travelTime; // Calculate new travel time

                // If the new travel time is less, update and add to the priority queue
                if (newTime < minTime.get(nextNode)) {
                    minTime.put(nextNode, newTime); // Update the shortest travel time for the adjacent node
                    minHeap.add(new int[] {nextNode, newTime}); // Add the adjacent node to the priority queue
                }
            }
        }

        // Calculate the maximum time to reach any node
        int maxTime = Collections.max(minTime.values());
        return maxTime == Integer.MAX_VALUE ? -1 : maxTime; // If any node is unreachable, return -1
    }
}
