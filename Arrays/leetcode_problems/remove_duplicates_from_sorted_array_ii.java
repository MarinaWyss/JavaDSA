// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/

// Time: O(n), Space O(1)
class Solution {
    public int removeDuplicates(int[] nums) {
        // Edge case: if the array is null or empty, return 0
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // If the array has less than 3 elements, it's already valid
        if (nums.length < 3) {
            return nums.length;
        }

        // The variable j is used to keep track of the position in the array
        // where the next valid element (adhering to the at-most twice rule) should be placed.
        // We start from index 2 because the first two elements can remain as they are.
        int j = 2;

        // Loop through the array starting from the third element (index 2)
        for (int i = 2; i < nums.length; i++) {
            // If the current element is not the same as the element at position j-2,
            // it means we can include this element in our valid array segment
            if (nums[i] != nums[j - 2]) {
                // Place the current element at position j and increment j
                nums[j] = nums[i];
                j++;
            }
        }
        // The final length of the modified array is equal to j,
        // which represents the number of valid elements adhering to the constraint.
        return j;
    }
}
