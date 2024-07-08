// https://leetcode.com/problems/path-with-maximum-probability/description/

import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

class Solution {
    /**
     * This class implements a modified version of Dijkstra's algorithm to find the path with the 
     * max prob of success in an undirected weighted graph. 
     * Instead of finding the shortest path, we maximize the product of success probs along the path.
     * 
     * Changes from standard Dijkstra's:
     * - Use a max-heap instead of a min-heap to prioritize paths with higher probs.
     * - Multiply probs instead of adding weights.
     * - Handle an undirected graph by adding each edge in both directions.
     * 
     * Time:
     * - Building the adjacency list: O(E), where E is the number of edges.
     * - The main loop processes each node and each edge once: O((V + E) log V), where V is the number of vertices and E is the number of edges.
     * 
     * Space:
     * - Adjacency list: O(V + E), to store all vertices and edges.
     * - Probability map: O(V), to store the maximum probability to each vertex.
     * - Priority queue: O(V), in the worst case, all nodes are added to the priority queue.
     * 
     * Example in case I forget how this works in the future:
     * - Given nodes: 0, 1, 2
     * - Edges: [[0, 1], [1, 2], [0, 2]]
     * - Probabilities: [0.5, 0.5, 0.2]
     * - Start: 0, End: 2
     * 
     * - Initial state:
     *   adj = {0: [[1, 0], [2, 2]], 1: [[0, 0], [2, 1]], 2: [[1, 1], [0, 2]]}
     *   maxHeap = [{1.0, 0}]
     *   maxProb = {0: 1.0}
     * 
     * - Process node 0:
     *   - From node 0 to 1: newProb = 1.0 * 0.5 = 0.5, add to maxHeap: [{0.5, 1}]
     *   - From node 0 to 2: newProb = 1.0 * 0.2 = 0.2, add to maxHeap: [{0.5, 1}, {0.2, 2}]
     * 
     * - Process node 1:
     *   - From node 1 to 2: newProb = 0.5 * 0.5 = 0.25, update maxHeap: [{0.25, 2}]
     * 
     * - Process node 2: end node reached, return 0.25
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Create an adjacency list to store the graph
        Map<Integer, List<int[]>> adj = new HashMap<>();
        // Initialize the adjacency list with empty lists for each node
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<int[]>());
        }

        // Fill the adjacency list with edges and their indices
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0]; // Source node
            int b = edges[i][1]; // Destination node
            adj.get(a).add(new int[] {b, i}); // Add edge a->b with index i
            adj.get(b).add(new int[] {a, i}); // Add edge b->a with index i (undirected graph)
        }

        // Create a map to store the maximum probability to each node
        Map<Integer, Double> maxProb = new HashMap<>();
        // Priority queue (max-heap) to order nodes by their probability of success
        Queue<double[]> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        // Add the starting node to the priority queue with a probability of 1
        maxHeap.add(new double[] {1.0, start_node});
        maxProb.put(start_node, 1.0);

        // Process the nodes in the priority queue
        while (!maxHeap.isEmpty()) {
            // Remove the node with the highest probability
            double[] cur = maxHeap.remove();
            double prob1 = cur[0]; // Current probability
            int node = (int) cur[1]; // Current node

            // If the current node is the end node, return the probability
            if (node == end_node) {
                return prob1;
            }

            // Process each adjacent node of the current node
            for (int[] neighbor : adj.get(node)) {
                int nextNode = neighbor[0]; // Adjacent node
                double prob2 = succProb[neighbor[1]]; // Probability of the edge to the adjacent node
                double newProb = prob1 * prob2; // Calculate new probability

                // If the new probability is greater, update and add to the priority queue
                if (newProb > maxProb.getOrDefault(nextNode, 0.0)) {
                    maxProb.put(nextNode, newProb); // Update the maximum probability for the adjacent node
                    maxHeap.add(new double[] {newProb, nextNode}); // Add the adjacent node to the priority queue
                }
            }
        }

        // If there's no path from start to end, return 0
        return 0.0;
    }
}
