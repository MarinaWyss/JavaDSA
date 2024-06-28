// num_connected_components_undirected_graph

// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Solution {
    class UnionFind {
        Map<Integer, Integer> parents;  // Map to store the parent of each node
        Map<Integer, Integer> rank;     // Map to store the rank of each node

        // Constructor to initialize the Union-Find structure
        public UnionFind(int n) {
            parents = new HashMap<>();  // Initialize the parent map
            rank = new HashMap<>();     // Initialize the rank map

            // Each node is its own parent initially and rank is 0
            for (int i = 0; i < n; i++) {
                parents.put(i, i);
                rank.put(i, 0);
            }
        }

        // Find function with path compression
        public int find(int n) {
            int p = parents.get(n);  // Get the parent of the node

            // Path compression: Make the parent of p point to its grandparent
            while (p != parents.get(p)) {
                parents.put(p, parents.get(parents.get(p)));
                p = parents.get(p);
            }
            return p;  // Return the root of the set
        }

        // Union function with union by rank
        public boolean union(int n1, int n2) {
            int p1 = find(n1);  // Find the root of the set containing n1
            int p2 = find(n2);  // Find the root of the set containing n2
            if (p1 == p2) return false;  // If they have the same root, they are already connected

            // Union by rank: Attach the smaller tree under the larger tree
            if (rank.get(p1) > rank.get(p2)) {
                parents.put(p2, p1);  // Attach p2's tree under p1's tree
            } else if (rank.get(p2) > rank.get(p1)) {
                parents.put(p1, p2);  // Attach p1's tree under p2's tree
            } else {
                parents.put(p2, p1);  // If ranks are the same, attach p2 under p1
                rank.put(p1, rank.get(p1) + 1);  // Increment the rank of the new root
            }
            return true;  // Return true indicating a successful union
        }
    }

    // Function to count the number of connected components
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);  // Initialize the Union-Find structure with n nodes

        // Process each edge to union the nodes
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);  // Union the two nodes in the edge
        }

        // Count unique roots to determine the number of connected components
        Set<Integer> rootSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            rootSet.add(uf.find(i));  // Find the root of each node and add it to the set
        }

        return rootSet.size();  // The size of the set represents the number of unique connected components
    }
}