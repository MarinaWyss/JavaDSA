// https://leetcode.com/problems/sort-colors/description/

class Solution {
    public void sortColors(int[] nums) {

        // 3 colors, but could also use maxVal + 1
        int numColors = 3;

        // Initialize buckets array to count occurrences
        int[] buckets = new int[numColors];
        for (int i = 0; i < numColors; i++) {
            buckets[i] = 0;
        }

        // Iterate through input array to count the number of times each value occurs
        for (int num : nums) {
            buckets[num]++;
        }

        // Fill each bucket in the original array
        int i = 0; // Pointer to keep track of next insertion point for nums
        // Loop through each color
        for (int c = 0; c < numColors; c++) {
            // While there is more than 0 of a color in the counts
            while (buckets[c] > 0) {
                nums[i] = c;  // Next spot in the input array is the color
                i++;  // Move to the right
                buckets[c]--; // Decrement count of that color
            }
        }
    }
}
