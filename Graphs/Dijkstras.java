import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;

public class Dijkstras {
    /**
     * This class implements Dijkstra's algorithm to find the shortest paths from a source node to all 
     * other nodes in a weighted graph.
     * The graph is represented using an adjacency list.
     * 
     * Time Complexity:
     * - Building the adjacency list: O(E), where E is the number of edges.
     * - The main loop processes each node and each edge once: O((V + E) log V), where V is the number of vertices (nodes) and E is the number of edges.
     *   - Each node is processed once and added to the priority queue (log V insertion time).
     *   - Each edge is processed once and may lead to an update in the priority queue (log V update time).
     * 
     * Space Complexity:
     * - Adjacency list: O(V + E), to store all vertices and edges.
     * - Shortest distance map: O(V), to store the shortest distance to each vertex.
     * - Priority queue: O(V), in the worst case, all nodes are added to the priority queue.
     */

    public static Map<Integer, Integer> shortestPath(int[][] edges, int n, int srcNode) {
        // Craete an adjacency list to store the graph
        Map<Integer, ArrayList<Integer[]>> adj = new HashMap<>();
        // Initialize adj list with empty lists for each node
        // Nodes often indexed at 1 so we start there
        for (int i = 1; i < n + 1; i++) {
            adj.put(i, new ArrayList<Integer[]>());
        }
        // Fill the adj list with the edges and their respective weights
        for (int[] edge : edges) {
            int src = edge[0];
            int dst = edge[1];
            int weight = edge[2];
            // Add a new array list to the values of src that includes the dst and weight
            adj.get(src).add(new Integer[] {dst, weight});
        }
        
        // Create a map to store the shortest distance to each node
        HashMap<Integer, Integer> shortest = new HashMap<>();
        // Create a priority queue (min-heap) to store the nodes to be processed
        // Order elements by the first element of the integer arrays (distance)
        // so we always retrieve elements with the smallest distance first
        Queue<int[]> minHeap = new PriorityQueue<>((n1, n2) -> (n1[0] - n2[0]));
        // Add the source node to the priority queue with a distance of 0
        minHeap.add(new int[] {0, srcNode});

        // Process the nodes in the priority queue
        while (!minHeap.isEmpty()) {
            // Remove the node with the smallest distance
            int[] cur = minHeap.remove();
            int w1 = cur[0];   // Distance to the current node
            int n1 = cur[1];   // Current node
            
            // If the current node is already processed, skip it
            if (shortest.containsKey(n1)) {
                continue;
            }

            // Mark the current node as processed with its shortest distance
            shortest.put(n1, w1);

            // Process each adjacent node of the current node
            for (Integer[] pair : adj.get(n1)) {
                int n2 = pair[0];   // Adjacent node
                int w2 = pair[1];   // Weight of the edge to the adjacent node
                // If the adjacent node is not processed, add it to the priority queue
                if (!shortest.containsKey(n2)) {
                    minHeap.add(new int[] {w1 + w2, n2});
                }
            }
        }
        return shortest;
    }
}
