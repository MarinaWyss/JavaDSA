// https://leetcode.com/problems/climbing-stairs/

/* You can reach the top of the staircase either by taking one step or two steps at a time.
If you are currently at step k, you could have arrived there from step k−1 or step 
k−2.

Thus, the number of distinct ways to reach step n (denoted as f(n)) 
can be expressed as the sum of the ways to reach the previous two steps:
f(n)=f(n−1)+f(n−2).

class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        else {
            return climbStairs(n-1) + climbStairs(n-2);
        }
    }
}

While this solution is correct, it is not the most efficient because it involves a lot of repeated calculations 
(overlapping subproblems). We get a TLE error on LeetCode for this approach. 

The memoization solution improves the recursive solution by using an unordered map (memo) to store the already-computed 
results for each step n, thereby avoiding reduntant calculations. Before making a recursive call, we check if 
the result for the given n exists in the memo. If it does, we return the stored value; otherwise, we compute the
result recursively and store it in the memo for future reference.
*/

import java.util.Map;
import java.util.HashMap;

class Solution {
    public int climbStairs(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        // call private helper method
        return climbStairs(n, memo);
    }

    private int climbStairs(int n, Map<Integer, Integer> memo) {
        if (n < 2) {
            return 1;
        }

        // If n is not in the memo map, compute the number of ways to climb n steps
        if (!memo.containsKey(n)) {
            memo.put(n, climbStairs(n-1, memo) + climbStairs(n-2, memo));
        }
        return memo.get(n);
    }
}
