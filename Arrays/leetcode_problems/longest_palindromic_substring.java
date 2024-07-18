// https://leetcode.com/problems/longest-palindromic-substring/description/


// Time: O(n^2), Space: O(1)
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Odd length centered around i
            int len1 = expandAroundCenter(s, i, i);
            // Even-length centered between index i and i+1
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            // Update the start and end indices if a longer palindrome is found
            if (len > end - start) {
                start = i - (len - 1) / 2;  // Subtract num characters to the left
                end = i + len / 2;  // Add num characters to the right of center
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        // Expand outwards while the characters on both sides are equal
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}