import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class GraphBreadthFirstSearch {
    public int graphBFS(String node, String target, HashMap<String, ArrayList<String>> adjList) {
        
        // Length of path
        int length = 0;
        // Set to keep track of visited nodes
        HashSet<String> visited = new HashSet<String>();
        // Queue for BFS
        Queue<String> queue = new LinkedList<String>();

        // Add the start node to the visited set and queue
        visited.add(node);
        queue.add(node);

        // Perform BFS
        while (!queue.isEmpty()) {
            // Number of nodes at the current level
            int size = queue.size();
            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                // Retrieve and remove the front element of the queue
                String curr = queue.poll();
                // If the target is found, return the length of the path
                if (curr.equals(target)) {
                    return length;
                }
                // Add all unvisited neighbors to the queue and mark them as visited
                for (String neighbor : adjList.get(curr)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                } 
            }
            // Increment the length of the path after processing one level
            length++;
        }
        // If the target is not found, return the length after processing all levels
        return length;
    }

    public static void main(String[] args) {
        // Example usage
        String[][] edges = {
            {"A", "B"},
            {"A", "C"},
            {"B", "D"},
            {"C", "D"},
            {"D", "E"}
        };

        AdjacencyList adjListBuilder = new AdjacencyList();
        HashMap<String, ArrayList<String>> adjList = adjListBuilder.buildAdjList(edges);

        GraphBreadthFirstSearch dfs = new GraphBreadthFirstSearch();
        String startNode = "A";
        String targetNode = "E";

        int pathLength = dfs.graphBFS(startNode, targetNode, adjList);
        System.out.println("Length of path from " + startNode + " to " + targetNode + ": " + pathLength);
    }
}
