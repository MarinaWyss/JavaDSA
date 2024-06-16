// https://leetcode.com/problems/longest-common-subsequence/description/

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        return helper(s1, s2);
    }

    public static int helper(char[] s1, char[] s2) {
        // Create a table to store the lengths
        int m = s1.length;
        int n = s2.length;
        // We add one to accomodate the base case of empty strings
        int[][] dp = new int[m + 1][n + 1];
    
        // Start iterating from the second row/col 
        // We skip the first because of the base case of an empty substring 
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If the characters match, add 1 to the result from the previous substrings
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else {
                    // Else, take the maximum value from
                    // either ignoring the current character of the 
                    // first string or the second string
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}

/*
Example walkthrough:

text1 = "abcde" and text2 = "ace"

Initialize dp table (m+1)x(n+1) with zeros:
    "" a  c  e
"" [0, 0, 0, 0]
a  [0, 0, 0, 0]
b  [0, 0, 0, 0]
c  [0, 0, 0, 0]
d  [0, 0, 0, 0]
e  [0, 0, 0, 0]

Fill dp table:
i=1, j=1: s1[0] == s2[0] -> 'a' == 'a'
    dp[1][1] = 1 + dp[0][0] = 1
    "" a  c  e
"" [0, 0, 0, 0]
a  [0, 1, 0, 0]
b  [0, 0, 0, 0]
c  [0, 0, 0, 0]
d  [0, 0, 0, 0]
e  [0, 0, 0, 0]

i=1, j=2: s1[0] != s2[1] -> 'a' != 'c'
    dp[1][2] = Math.max(dp[0][2], dp[1][1]) = 1
    "" a  c  e
"" [0, 0, 0, 0]
a  [0, 1, 1, 0]
b  [0, 0, 0, 0]
c  [0, 0, 0, 0]
d  [0, 0, 0, 0]
e  [0, 0, 0, 0]

i=1, j=3: s1[0] != s2[2] -> 'a' != 'e'
    dp[1][3] = Math.max(dp[0][3], dp[1][2]) = 1
    "" a  c  e
"" [0, 0, 0, 0]
a  [0, 1, 1, 1]
b  [0, 0, 0, 0]
c  [0, 0, 0, 0]
d  [0, 0, 0, 0]
e  [0, 0, 0, 0]

i=2, j=1: s1[1] != s2[0] -> 'b' != 'a'
    dp[2][1] = Math.max(dp[1][1], dp[2][0]) = 1
    "" a  c  e
"" [0, 0, 0, 0]
a  [0, 1, 1, 1]
b  [0, 1, 0, 0]
c  [0, 0, 0, 0]
d  [0, 0, 0, 0]
e  [0, 0, 0, 0]

i=2, j=2: s1[1] != s2[1] -> 'b' != 'c'
    dp[2][2] = Math.max(dp[1][2], dp[2][1]) = 1
    "" a  c  e
"" [0, 0, 0, 0]
a  [0, 1, 1, 1]
b  [0, 1, 1, 0]
c  [0, 0, 0, 0]
d  [0, 0, 0, 0]
e  [0, 0, 0, 0]

i=2, j=3: s1[1] != s2[2] -> 'b' != 'e'
    dp[2][3] = Math.max(dp[1][3], dp[2][2]) = 1
    "" a  c  e
"" [0, 0, 0, 0]
a  [0, 1, 1, 1]
b  [0, 1, 1, 1]
c  [0, 0, 0, 0]
d  [0, 0, 0, 0]
e  [0, 0, 0, 0]

Continue filling the dp table similarly for the remaining characters...
*/