// https://leetcode.com/problems/redundant-connection/description/

import java.util.HashMap;
import java.util.Map;

class UnionFind {
    private Map<Integer, Integer> parents;
    private Map<Integer, Integer> rank;

    public UnionFind(int n) {
        parents = new HashMap<>();
        rank = new HashMap<>();
        // The problem states nodes are labeled 1 - n, so we loop from 1
        for (int i = 1; i <= n; i++) {
            parents.put(i, i);
            rank.put(i, 0);
        }
    }

    public int find(int n) {
        int p = parents.get(n);
        if (p != n) {
            parents.put(n, find(p));  // Path compression
        }
        return parents.get(n);  // Return the root of the set
    }

    public boolean union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);
        if (p1 == p2) {
            return false;  // Cycle detected
        }

        if (rank.get(p1) > rank.get(p2)) {
            parents.put(p2, p1);
        } else if (rank.get(p1) < rank.get(p2)) {
            parents.put(p1, p2);
        } else {
            parents.put(p1, p2);
            rank.put(p2, rank.get(p2) + 1);
        }
        return true;
    }
}

public class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {  // Iterate over each edge in the graph
            if (!uf.union(edge[0], edge[1])) {  // If union operation fails (indicating a cycle)
                return edge;  // Return the edge that creates the cycle
            }
        }
        return new int[0];  // In case no cycle is found (should not happen in this problem)
    }
}