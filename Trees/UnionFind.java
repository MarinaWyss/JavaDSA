import java.util.Map;
import java.util.HashMap;


public class UnionFind {
    
    Map<Integer, Integer> parents;  // Could also use arrays, O(n) in either case
    Map<Integer, Integer> rank;

    public UnionFind(int n) {
        parents = new HashMap<>(); // Map to store the parent of each element
        rank = new HashMap<>();  // Map to store the rank (or depth) of each element's tree

        for (int i = 1; i < n + 1; i++) {  // Loop through each element from 1 to n
            parents.put(i, i);  // Set the parent of each element to itself
            rank.put(i, 0);  // Initialize the rank of each element to 0
        }
    }

    /**
     * Find the representative (root) of the set containing 'n' using path compression.
     * The idea is to make the tree representing the set flatter, by making nodes point directly to the root.
     * This is achieved by traversing the tree and updating the parent of each visited node to point to the root.
     * Path compression helps to speed up future find operations, reducing the time complexity to nearly constant.
     * 
     * So without optimizations O(n), with close to constant. Technically it's O(a(n)) where a is the inverse
     * Ackermann function, which grows slowly, so in practical terms it's nearly constant.
     */
    public int find(int n) {
        // Get the parent of the vertex 'n'
        int p = parents.get(n);

        while (p != parents.get(p)) {  // Loop until we reach the root of the set
            // Perform path compression by pointing the current element to its grandparent
            parents.put(p, parents.get(parents.get(p)));  // Update the parent of 'p' to its grandparent
            p = parents.get(p);  // Move to the next parent
        }
        return p;  // Return the root of the set
    }

    // Union the sets containing 'n1' and 'n2' by rank
    // Naive implementation is O(n), with rank it's O(a(n))/constant
    public boolean union(int n1, int n2) {
        int p1 = find(n1);  // Find the root of the set containing 'n1'
        int p2 = find(n2);  // Find the root of the set containing 'n2'
        if (p1 == p2) return false;  // If they are in the same set, return false since no union was performed

        if (rank.get(p1) > rank.get(p2)) {  // If the rank of p1 is greater than the rank of p2
            parents.put(p2, p1);  // Make p1 the parent of p2
        } else if (rank.get(p1) < rank.get(p2)) {  // If the rank of p1 is less than the rank of p2
            parents.put(p1, p2);  // Make p2 the parent of p1
        } else {  // If the ranks are equal
            parents.put(p1, p2);  // Arbitratily make p2 the parent of p1
            rank.put(p2, rank.get(p2) + 1);  // Increase the rank of p2 by 1
        }
        return true;  // Return true to indicate a successful union
    }
}
