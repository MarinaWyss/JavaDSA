// https://leetcode.com/problems/reverse-bits/description/

public class Solution {
    public int reverseBits(int n) {
        int res = 0;

        // Iterate over each bit of the input number
        for (int i = 0; i < 32; i++) {
            // Extract the bit at position i
            // Shift n to the right by i positions and check if it is set (1)
            int bit = (n >> i) & 1;
            // Set the bit at position 31 - i in the result to swap positions
            // Also, use bitwise OR to convert bit from 0 to 1 or 1 to 0
            res |= (bit << (31-i));
        } 
        return res;
    }
}
