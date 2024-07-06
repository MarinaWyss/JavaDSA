// https://leetcode.com/problems/permutations-ii/description/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Sort array so duplicates are next to each other
        Arrays.sort(nums);
        
        List<List<Integer>> perms = new ArrayList<>(); // Initialize the list to hold permutations.
        perms.add(new ArrayList<>()); // Start with an empty permutation.

        // Iterate over each number in the input array.
        for (int n : nums) {
            List<List<Integer>> nextPerms = new ArrayList<>(); // Temporary list to hold the new permutations.
            // Iterate over each permutation in the current list of permutations.
            for (List<Integer> p : perms) {
                // Insert the current number into every possible position of the current permutation.
                for (int j = 0; j <= p.size(); j++) { 
                    List<Integer> pCopy = new ArrayList<>(p); // Create a new list (copy) to avoid modifying the original permutation.
                    pCopy.add(j, n); // Insert the current number at position j in the copy.
                    nextPerms.add(pCopy); // Add the new permutation to the temporary list.
                    // Skip duplicates: if the next position is the same as the current one, break
                    if (j < p.size() && p.get(j) == n) {
                        break;
                    }
                }
            }
            perms = nextPerms; // Update the list of permutations with the new permutations.
        }
        return perms;
    }
}