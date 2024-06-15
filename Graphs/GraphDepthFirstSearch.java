import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

public class GraphDepthFirstSearch {
    public int graphDFS(String node, String target, HashMap<String, ArrayList<String>> adjList, HashSet<String> visited) {
        // If we've already visited a node then we didn't find a path
        if (visited.contains(node)) {
            return 0;
        }

        // If we found the target, we did find a path
        if (node == target) {
            return 1;
        }

        int numPaths = 0;
        visited = new HashSet<String>();
        // Add the current node to the set of visited nodes
        visited.add(node);

        // Iterate through all the neighbors of the current node
        for (String neighbor : adjList.get(node)) {
            // Recursively perform DFS on each neighbor and accumulate the number of paths found
            numPaths += graphDFS(neighbor, target, adjList, visited);
        }
        // Remove the current node from the set of visited nodes (backtracking)
        visited.remove(node);
        return numPaths;
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

        GraphDepthFirstSearch dfs = new GraphDepthFirstSearch();
        HashSet<String> visited = new HashSet<>();
        String startNode = "A";
        String targetNode = "E";

        int numPaths = dfs.graphDFS(startNode, targetNode, adjList, visited);
        System.out.println("Number of paths from " + startNode + " to " + targetNode + ": " + numPaths);
    }
}