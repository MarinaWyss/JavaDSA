// https://leetcode.com/problems/number-of-1-bits/

class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n > 0) {
            // Bitwise AND n with 1 to see if they are both 1.
            if ((n & 1) == 1) count++;
            // Shift n to the right (same as / 2)
            n = n >> 1;
        }
        return count;
    }
}