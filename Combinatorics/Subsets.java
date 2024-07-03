import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// Given a list of nums that are not necessarily distinct, return all possible distinct subsets.
public class Subsets {
    
    // Time: O(n * 2^n)
    // Space: O(n)
    public static List<List<Integer>> subsets(int[] nums) {
        // Sort array so duplicates are next to each other
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();  // All possible subsets
        List<Integer> curSet = new ArrayList<>();  // Current subset being constructed
        helper(0, nums, curSet, subsets);  // Recursively construct subsets
        return subsets;
    }

    public static void helper(int i, int[] nums, List<Integer> curSet, List<List<Integer>> subsets) {
        // Each element can either be in a subset or not. We do the following to construct
        // all possible combos:
        // a. Include the current element in the subset
        curSet.add(nums[i]);
        // Recursively call the helper method to continue with the next element
        helper(i + 1, nums, curSet, subsets);

        // b. Exclude the current element from the subset
        curSet.remove(curSet.size() - 1); // Undo the inclusion of nums[i]

        // If we run into duplicates, skip over them until we are passed them
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }
        helper(i + 1, nums, curSet, subsets);
    }
}
