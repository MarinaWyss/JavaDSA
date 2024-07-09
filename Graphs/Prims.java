import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

public class Prims {
    /*
    * Prim's Algorithm for Minimum Spanning Tree (MST)
    *
    * Finds the minimum spanning tree of a connected, undirected graph.
    * Starts with an arbitrary node and grows the MST by repeatedly adding the 
    * smallest edge that connects a vertex in the MST to a vertex outside of it.
    */
    public static List<Integer[]> mst(int[][] edges, int n) {
        // Build adjacency list
        // adj is a hashmap where every key is a vertex in the graph,
        // and the values are the neighboring vertices and their weights
        Map<Integer, ArrayList<Integer[]>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adj.put(i, new ArrayList<Integer[]>()); // Initializing adjacency list for each vertex
        }
        for (int[] edge : edges) {
            int n1 = edge[0]; // First vertex of the edge
            int n2 = edge[1]; // Second vertex of the edge
            int weight = edge[2]; // Weight of the edge
            // Since it's an undirected graph we add both directions
            adj.get(n1).add(new Integer[] {n2, weight}); // Adding edge n1 -> n2 with weight
            adj.get(n2).add(new Integer[] {n1, weight}); // Adding edge n2 -> n1 with weight
        }

        // We will use a minheap with the weights as keys
        // Initialize it with a single node and then push neighbors
        Queue<int[]> minHeap = new PriorityQueue<>((n1, n2) -> (n1[0] - n2[0]));
        for (Integer[] neighbor : adj.get(1)) {
            int node = neighbor[0], weight = neighbor[1];
            minHeap.add(new int[] {weight, 1, node}); // Adding edges connected to the starting vertex (vertex 1)
        }

        List<Integer[]> mst = new ArrayList<>(); // List to store the MST edges
        HashSet<Integer> visited = new HashSet<>(); // Set to keep track of visited vertices
        visited.add(1); // Starting with vertex 1

        while (visited.size() < n) { // Loop until all vertices are visited
            int[] cur = minHeap.remove(); // Get the edge with the smallest weight
            int n1 = cur[1]; // First vertex of the edge
            int n2 = cur[2]; // Second vertex of the edge

            if (visited.contains(n2)) { // Skip if the vertex is already visited
                continue;
            }

            mst.add(new Integer[] {n1, n2}); // Add the edge to the MST
            visited.add(n2); // Mark the vertex as visited

            for (Integer[] pair : adj.get(n2)) { // Iterate through the neighbors of the newly added vertex
                Integer neighbor = pair[0], weight = pair[1];
                if (!visited.contains(neighbor)) { // Add the edge to the min-heap if the neighbor is not visited
                    minHeap.add(new int[] {weight, n2, neighbor});
                }
            }
        }
        return mst;
    }
}