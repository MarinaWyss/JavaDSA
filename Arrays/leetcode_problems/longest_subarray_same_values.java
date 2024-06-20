/* Q: Find the length of the longest subarray with the same value in each position.

To do this, we can run a for loop on our array and keep expanding our window from the right, 
until we encounter a value we haven't encountered before. We then shrink our window by 
bringing our L pointer to our R pointer. We don't need to increment L because if we 
encountered a new value, it must be the case that every value before was a duplicate. 
We then repeatedly calculate the length of our window by taking the maximum of the 
current length and the maximum length. This is done using R - L + 1 which ensures 
both indices are inclusive.

Note: This could be done without two pointers, this is just to illustrate the idea.

Time: O(n)
Space: O(1) -- no data structures grow with input size.
*/

public class Solution {
    public int longestSubarray(int[] nums) {
        int length = 0;
        int L = 0;

        for (int R = 0; R < nums.length; R++) {
            if (nums[L] != nums[R]) {
                L = R; // We broke the pattern so left needs to catch up to right
            }
            length = Math.max(length, R - L + 1);  
        }
        return length;
    }
}