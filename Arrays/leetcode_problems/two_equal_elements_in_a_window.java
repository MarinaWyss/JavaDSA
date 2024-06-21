import java.util.HashSet;

// Q: Given an array, return true if there are two elements within a window of size k that are equal.

public class Solution {
    
    // Time: O(n * k) -- so not too bad small windows
    // Space: O(1)
    public boolean bruteForce(int[] nums, int k) {
        // Initialize a left pointer 
        for (int L = 0; L < nums.length; L++) {
            // Initialize a right pointer at the beginning and loop either to the 
            // end of the array or to the end of the window
            for (int R = L; R < Math.min(nums.length, L + k); R++) {
                if (nums[L] == nums[R]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Detecting duplicates can be optimized with HashSets
    // Instead of manually comparing every element in the array to every other
    // value in window, we can keep a rolling hash set of our window
    // Time: O(n), Space: O(k)
    public boolean hashSetWindow(int[] nums, int k) {
        // Keep track of the current elements in the window
        HashSet<Integer> window = new HashSet<>();
        int L = 0;

        for (int R = 0; R < nums.length; R++) {
            // If the current window size is larger than k, remove the left-most 
            // element and increment the left pointer
            if (R - L + 1 > k) {
                window.remove(nums[L]);
                L++;
            }
            // If we find a duplicate, return true
            if (window.contains(nums[R])) {
                return true;
            }
            // Add the current element to the window
            window.add(nums[R]);
        }
        // If no duplicates were found, return false
        return false;
    }
}
