// https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/

class Solution {
    public int removeDuplicates(int[] nums) {
        // Check for empty array
        if (nums.length == 0){
            return 0;
        }
        
        int j = 1;  // Pointer for placing the next unique element
        for (int i = 1; i < nums.length; i++) { // Pointer for iterating through the array
            if (nums[i] != nums[i - 1]) { // Check if the current element is unique
                nums[j] = nums[i]; // Place the unique element at position j
                j++; // Move the placement pointer to the next position
            }
        }
        return j; // The length of the array without duplicates
    }
}