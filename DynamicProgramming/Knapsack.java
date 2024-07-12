import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Knapsack {
    /*Q: Given a list of N items, and a backpack with a limited capacity, 
    return the maximum total profit that can be contained in the backpack. 
    The i-th item's profit is profit[i] and its weight is weight[i]. Assume 
    you can only add each item to the bag at most once.
    */

    // Brute force
    // Time: O(2^n), Space: O(n) - where n is the number of items
    public static int dfs(List<Integer> profit, List<Integer> weight, int capacity) {
        return dfsHelper(0, profit, weight, capacity);
    }

    public static int dfsHelper(int i, List<Integer> profit, List<Integer> weight, int capacity) {
        // Base case, we exhaust all items
        if (i == profit.size()) {
            return 0;
        }

        // If we skip item i
        int maxProfit = dfsHelper(i + 1, profit, weight, capacity);

        // If we include item i
        int newCapacity = capacity - weight.get(i);
        if (newCapacity >= 0) {
            int p = profit.get(i) + dfsHelper(i + 1, profit, weight, newCapacity);
            maxProfit = Math.max(maxProfit, p);
        }
        return maxProfit;
    }

    // In the above solution we re-compute values multiple times. We can use
    // memoization to improve on this.
    // Time: O(n * m), Space: O(n * m) where n is the num items and m is the capacity
    public static int memoization(List<Integer> profit, List<Integer> weight, int capacity) {
        
        // Build a 2D array with the num items as rows and capcity + 1 cols
        // Fill with -1 to start
        List<Integer[]> cache = new ArrayList<>(); 
        for (int row = 0; row < profit.size(); row++) {
            cache.add(row, new Integer[capacity + 1]);
            Arrays.fill(cache.get(row), -1);
        }
        return memoHelper(0, profit, weight, capacity, cache);
    }

    public static int memoHelper(int i, List<Integer> profit, List<Integer> weight, int capacity, List<Integer[]> cache) {
        // Base case #1, we hit the limit of our capacity
        if (i == profit.size()) {
            return 0;
        }

        // Base case #2, if the cache already contains a value that is not -1
        // meaning we've processed this value before and can return it directly
        if (cache.get(i)[capacity] != -1) {
            return cache.get(i)[capacity];
        }

        // Case where we skip i
        cache.get(i)[capacity] = memoHelper(i + 1, profit, weight, capacity, cache);

        // Case where we include i
        int newCapacity = capacity - weight.get(i);
        if (newCapacity >= 0) {
            int p = profit.get(i) + memoHelper(i + 1, profit, weight, newCapacity, cache);
            cache.get(i)[capacity] = Math.max(cache.get(i)[capacity], p);
        }
        return cache.get(i)[capacity];
    }

    // Dynamic programming solution
    // Time: O(n * m), Space: O(n * m) where n is the num items and m is the capacity
    public static int dp(List<Integer> profit, List<Integer> weight, int capacity) {
        /*
        * - We use a 2D array `dp` where `dp[i][c]` represents the maximum profit that can be obtained
        *   with the first `i` items and capacity `c`.
        * - For each item and capacity, we decide whether to include the item or not to maximize profit.
        * - If the item is included, we add its profit to the maximum profit achievable with the remaining capacity.
        * - If the item is not included, the maximum profit is the same as without the current item.
        * - The result is found in `dp[n-1][m]` where `n` is the number of items and `m` is the capacity.
        */
        
    // Create a 2D array `dp` where rows represent items and columns represent capacities
    // Initialize all values to 0
    List<Integer[]> dp = new ArrayList<>();
    for (int row = 0; row < profit.size(); row++) {
        dp.add(row, new Integer[capacity + 1]);
        Arrays.fill(dp.get(row), 0); // Fill each row with zeros initially
    }

    // Fill the first column with 0 as no items can be included if capacity is 0
    for (int i = 0; i < profit.size(); i++) {
        dp.get(i)[0] = 0;
    }
    
    // Initialize the first row (considering the first item) based on its weight and profit
    for (int c = 0; c <= capacity; c++) {
        if (weight.get(0) <= c) { // If the capacity allows, include the first item
            dp.get(0)[c] = profit.get(0);
        }
    }

    // Process the rest of the items and capacities
    for (int i = 1; i < profit.size(); i++) {
        for (int c = 1; c <= capacity; c++) {
            int skip = dp.get(i-1)[c]; // Option to not include the current item
            int include = 0;
            if (c - weight.get(i) >= 0) { // Option to include the current item, if capacity allows
                include = profit.get(i) + dp.get(i-1)[c - weight.get(i)];
            }
            // Take the maximum of including or not including the current item
            dp.get(i)[c] = Math.max(include, skip);
        }
    }
    
    // The last cell of the dp array contains the maximum profit for all items and the full capacity
    return dp.get(profit.size() - 1)[capacity];
    }
}