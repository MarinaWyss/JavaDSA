import java.util.List;
import java.util.ArrayList;

public class Combinations {

    // Return all possible combos of size k from n numbers
    // Time: O(n * 2^n)
    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> combos = new ArrayList<>();
        helper(1, new ArrayList<>(), combos, n, k);
        return combos;
    }

    public static void helper(int i, List<Integer> curCombo, List<List<Integer>> combos, int n, int k) {
        // Base case: If the current combination has reached the desired size k
        if (curCombo.size() == k) {
            // Add a copy of the current combo to the list of combos
            combos.add(new ArrayList<>(curCombo));
            return;
        }

        // We've explored everything
        if (i > n) return;

        // Each element can either be in a subset or not. We do the following to construct
        // all possible combos:
        // a. Include the current element in the combination
        curCombo.add(i);
        // Recursively call the helper method to continue with the next element
        helper(i + 1, curCombo, combos, n, k);

        // b. Exclude the current element from the combination
        // Backtrack
        curCombo.remove(curCombo.size() - 1);
        // Recursively call the helper method to continue with the next element
        helper(i + 1, curCombo, combos, n, k);
    }

    // Optimized version using n choose k/binomial coefficient C(n, k) formula
    // Time: O(n * C(n, k))
    public static List<List<Integer>> combinationsOptimized(int n, int k) {
        List<List<Integer>> combos = new ArrayList<>();
        helperOpt(1, new ArrayList<>(), combos, n, k);
        return combos;
    }

    public static void helperOpt(int i, List<Integer> curCombo, List<List<Integer>> combos, int n, int k) {
        // Base case: If the current combination has reached the desired size k
        if (curCombo.size() == k) {
            // Add a copy of the current combo to the list of combos
            combos.add(new ArrayList<>(curCombo));
            return;
        }

        // We've explored everything
        if (i > n) return;

        // Iterate from the current number i to n
        for (int j = i; j < n; j++) {
            // Add the current number to the combination
            curCombo.add(j);
            // Recursively call the helper function with the next number
            helperOpt(j + 1, curCombo, combos, n, k);
            // Remove the last added number to backtrack
            curCombo.remove(curCombo.size() - 1);
        }
    }
    
}
