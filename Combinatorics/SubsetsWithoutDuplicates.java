import java.util.List;
import java.util.ArrayList;

// Generate all possible subsets of an array of integers
// Assumes we do not have duplicate elements in the input
public class SubsetsWithoutDuplicates {

    // Time: O(n * 2^n)
    // Space: O(n)
    /* 
     * At every single element, we can either choose to include or not include that 
     * specific element.
     */
    public static List<List<Integer>> subsetsWithoutDuplicates(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();  // All possible subsets
        List<Integer> curSet = new ArrayList<>();  // Current subset being constructed
        helper(0, nums, curSet, subsets);  // Recursively construct subsets
        return subsets;
    }

    public static void helper(int i, int[] nums, List<Integer> curSet, List<List<Integer>> subsets) {
        // If the initial index >= nums.length, we've hit the base case
        if (i >= nums.length) {
            // Add a copy of the current subset to the list of subsets
            subsets.add(new ArrayList<>(curSet));
            return;
        }

        // Each element can either be in a subset or not. We do the following to construct
        // all possible combos:
        // a. Include the current element in the subset
        curSet.add(nums[i]);
        // Recursively call the helper method to continue with the next element
        helper(i + 1, nums, curSet, subsets);

        // b. Exclude the current element from the subset
        curSet.remove(curSet.size() - 1); // Undo the inclusion of nums[i]
        // Recursively call the helper method to continue with the next element
        helper(i + 1, nums, curSet, subsets);
    }
}
