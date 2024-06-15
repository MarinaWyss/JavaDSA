import java.util.HashMap;
import java.util.ArrayList;

public class AdjacencyList {

    public HashMap<String, ArrayList<String>> buildAdjList(String[][] edges) {
        
        HashMap<String, ArrayList<String>> adjList = new HashMap<>();
        
        for (String[] edge : edges) {
            String src = edge[0], dst = edge[1];
            // If the edges aren't in the adjacency list yet, add 
            // them with an empty list as a placeholder
            if (!adjList.containsKey(src)) {
                adjList.put(src, new ArrayList<String>());
            }
            if (!adjList.containsKey(dst)) {
                adjList.put(dst, new ArrayList<String>());
            }
            // Add the mapping
            adjList.get(src).add(dst);
        }
        return adjList;
    }
}