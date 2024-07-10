import java.util.Set; 
import java.util.HashSet; 
import java.util.Map; 
import java.util.HashMap; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Collections; 

public class TopologicalSort {
    /*
     * Given a DAG, return a valid topological ordering.
     * 
     * The approach used here is DFS. We traverse each node,
     * marking it as visited and then recursively visiting its neighbors. After
     * visiting all neighbors of a node, we add the node to the result list.
     * Finally, we reverse the result list to get the correct topological order.
     * 
     * Example:
     * Input: edges = {{1, 2}, {1, 3}, {3, 4}, {2, 4}}, n = 4
     * Output: [1, 3, 2, 4] or [1, 2, 3, 4] 
     * Both are valid topological orders.
     */
    public static List<Integer> topologicalSort(int[][] edges, int n) {
        // Create an adjacency list to represent the graph
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adj.put(i, new ArrayList<>());
        }
        // Populate the adjacency list with the given edges
        for (int[] edge : edges) {
            int src = edge[0];
            int dst = edge[1];
            adj.get(src).add(dst);
        }
        // List to store the topological order
        List<Integer> result = new ArrayList<>();
        // Set to keep track of visited nodes to avoid cycles and reprocessing
        Set<Integer> visited = new HashSet<>();
        // Perform DFS from each node to ensure all nodes are processed
        for (int i = 1; i <= n; i++) {
            dfs(i, adj, visited, result);
        }
        // Reverse the result list to get the correct topological order
        Collections.reverse(result);
        return result;
    }

    public static void dfs(int src, Map<Integer, ArrayList<Integer>> adj, Set<Integer> visited, List<Integer> result) {
        // If the node has already been visited, return
        if (visited.contains(src)) {
            return;
        }
        // Mark the current node as visited
        visited.add(src);
        // Recursively visit all neighbors of the current node
        for (int neighbor : adj.get(src)) {
            dfs(neighbor, adj, visited, result);
        }
        // Add the current node to the result list AFTER visiting its neighbors (post-order)
        // This is necessary for topological ordering
        result.add(src);
        return;
    }
}
