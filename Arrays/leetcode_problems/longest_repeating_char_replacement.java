// https://leetcode.com/problems/longest-repeating-character-replacement/description/

/*
The algorithm uses a sliding window to maintain the current substring under consideration. 
The window is expanded by moving the right pointer R, and it is contracted by 
moving the left pointer L when the condition (R - L + 1 - maxChar) > k is met.

The countArr keeps track of the frequency of each character within the current window. 
By updating this array and tracking the maximum frequency of any character within 
the window (maxChar), the algorithm can efficiently check the validity of the window.

The condition (R - L + 1 - maxChar) > k checks if the number of characters 
that need to be replaced to make all characters in the window the same exceeds k. 
If it does, the window is adjusted by moving the left pointer.

By continually updating the result with the maximum length of valid windows, the 
algorithm ensures that it finds the longest possible substring that meets the criteria.
 */

class Solution {
    public int characterReplacement(String s, int k) {
        // Store count of characters in the current window
        // 26 possible uppercase letters
        int[] countArr = new int[26];

        int res = 0;  // Max length of substring
        int maxChar = 0; // Max frequency of any single character in the current window
        int L = 0;
        
        for (int R = 0; R < s.length(); R++) {
            // Count the frequency of the char at position R
            // Note: The ASCII value of 'A' is 65, 'B' is 66, and so on up to 'Z', which is 90. 
            // By subtracting 'A' from any uppercase letter, you  normalize values to the range 0 to 25.
            countArr[s.charAt(R) - 'A']++;

            // Find the char with the max number of occurrences in the current window
            maxChar = Math.max(maxChar, countArr[s.charAt(R) - 'A']);

            // Check if current window is valid
            // i.e. if the current window size minus the frequency of the most 
            // frequent character is greater than k
            if ((R - L + 1 - maxChar) > k) {
                // If it is not, we need to decrement the count of the char 
                // at L because it is no longer in the window
                countArr[s.charAt(L) - 'A']--;
                // And shift L over to the right
                L++;
            }
            // Max length of a valid window found so far
            res = Math.max(res, R - L + 1);
        }
        return res;
    }
}
