import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

public class Kruskals {
    /*
     * Given a list of edges of a connected undirected graph
     * with nodes 1 to n, return a list of edges making up the MST
     */
    public static List<Integer[]> mst(int[][] edges, int n) {
        // Use a min-heap to always get the next smallest weight
        // Note: We don't need an adjacency list because we don't care about picking
        // the smallest weight from connected components, but the smallest weight
        // of all vertices
        Queue<int[]> minHeap = new PriorityQueue<>((n1, n2) -> (n1[0] - n2[0]));
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            int weight = edge[2];  // Cost to get from n1 -> n2
            minHeap.add(new int[] {weight, n1, n2});
        }

        UnionFind unionFind = new UnionFind(n);  // To manage connected components
        List<Integer[]> mst = new ArrayList<>();  // To store edges of final MST
        
        // Continue until the MST has n-1 edges
        while (mst.size() < n - 1) {
            // Remove edge with smallest weight from min-heap
            int[] cur = minHeap.remove();
            int n1 = cur[0];
            int n2 = cur[1];

            // If n1 and n2 are already connected, skip this edge to avoid cycles
            if (unionFind.union(n1, n2) == false) {
                continue;
            }
            // Add current edge to MST
            mst.add(new Integer[] {n1, n2});
        }
        return mst;
    }  
}

/* EXAMPLE
* Edges: [[1, 2, 1], [1, 3, 4], [2, 3, 2], [2, 4, 3], [3, 4, 5]]
* 1. Edge [1, 2] with weight 1 - Add to MST
* 2. Edge [2, 3] with weight 2 - Add to MST
* 3. Edge [2, 4] with weight 3 - Add to MST
* 4. Edge [1, 3] with weight 4 - Skip (would create a cycle)
* 5. Edge [3, 4] with weight 5 - Skip (would create a cycle)
* 
* The resulting MST will have the edges: [[1, 2], [2, 3], [2, 4]]
*/
