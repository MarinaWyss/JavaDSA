// https://leetcode.com/problems/longest-substring-without-repeating-characters/

import java.util.HashSet;
import java.util.Set;

/*
 * The algorithm uses two pointers, L (left) and R (right), to represent the current 
 * window of characters being examined. Initially, both pointers start at the beginning of the string.
 * 
 * The right pointer R iterates over each character in the string. For each character, 
 * it checks if that character is already in the current window (tracked by a set).
 * 
 * If the character at R is already in the set (indicating a duplicate), the algorithm enters a 
 * while loop that removes characters from the set starting from the left pointer L and increments 
 * L until the duplicate character is removed from the window. This effectively shrinks the window 
 * from the left side, ensuring that all characters within the window are unique.
 * 
 * Once the duplicate character is removed (if necessary), the character at R is added to the set, 
 * effectively expanding the window to include this new character.
 * 
 * After updating the set, the algorithm calculates the current window size (R - L + 1) and 
 * updates the maximum length found so far if the current window is larger.
 * 
 * Time: O(n)
 * Space: The space complexity is O(min(n, m)), where n is the length of the string and m is the size of 
 * the character set (e.g., 26 for lowercase English letters). 
 * This is because the set stores the characters in the current window.
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int length = 0;
        int L = 0;
        // Store unique characters in the current window
        Set<Character> seen = new HashSet<>();

        // Iterate with the right pointer from the start to the end of the string
        for (int R = 0; R < s.length(); R++) {
            // While the current character is already in the set (duplicate found)
            while (seen.contains(s.charAt(R))) { 
                // Remove the character at the left pointer from the set
                seen.remove(s.charAt(L)); 
                // Move the left pointer to the right to reduce the window size
                L++; 
            }
            // Add the current character to the set
            seen.add(s.charAt(R));
             // Update the maximum length with the current window size if it is larger
            length = Math.max(length, R - L + 1);
        }
        return length;
    }
}

/*
 * Example:
 *    Input: "abcabcbb"

   Initial State:
   L = 0, R = 0, seen = {}, length = 0

   Iteration with R:
   R = 0: Add 'a' to the set -> seen = {a}, length = 1
   R = 1: Add 'b' to the set -> seen = {a, b}, length = 2
   R = 2: Add 'c' to the set -> seen = {a, b, c}, length = 3
   R = 3: 'a' is already in the set, enter while loop:
       Remove 'a' (L=0), move L to 1 -> seen = {b, c}
       Exit loop, add 'a' to the set -> seen = {b, c, a}, length remains 3
   R = 4: 'b' is already in the set, enter while loop:
       Remove 'b' (L=1), move L to 2 -> seen = {c, a}
       Exit loop, add 'b' to the set -> seen = {c, a, b}, length remains 3
   R = 5: 'c' is already in the set, enter while loop:
       Remove 'c' (L=2), move L to 3 -> seen = {a, b}
       Exit loop, add 'c' to the set -> seen = {a, b, c}, length remains 3
   R = 6: 'b' is already in the set, enter while loop:
       Remove 'a' (L=3), move L to 4 -> seen = {b, c}
       Remove 'b' (L=4), move L to 5 -> seen = {c}
       Exit loop, add 'b' to the set -> seen = {c, b}, length remains 3
   R = 7: 'b' is already in the set, enter while loop:
       Remove 'c' (L=5), move L to 6 -> seen = {b}
       Remove 'b' (L=6), move L to 7 -> seen = {}
       Exit loop, add 'b' to the set -> seen = {b}, length remains 3 
 */