import java.util.List;
import java.util.ArrayList;

// Q: Given a list of nums, return all possible distinct permutations of nums.
class Permutations {
    // Time Complexity: O(n^2 * n!) where n is the number of elements in the input array.
    //  This is because there are n! permutations and inserting an element into a list takes O(n) time.
    public static List<List<Integer>> permRecursive(int[] nums) {
        return helperRecursive(0, nums);
    }

    public static List<List<Integer>> helperRecursive(int i, int[] nums) {
        // Base Case: When the index i reaches the length of the nums array, 
        // it means we have considered all elements, so we return a list containing an
        // empty list as the initial permutation.
        if (i == nums.length) {
            List<List<Integer>> res = new ArrayList<>(); // Initialize a list to hold the result.
            res.add(new ArrayList<>()); // Add an empty list as a starting permutation.
            return res;
        }

        List<List<Integer>> resPerms = new ArrayList<>(); // List to hold the resulting permutations.
        /*
        * For each recursive call, we generate permutations for the elements starting from index i+1
        * to the end of the array. We then insert the element nums[i] into every possible position of each of these permutations.
        * This is done using nested loops:
        *     1. The outer loop iterates over each permutation returned by the recursive call.
        *     2. The inner loop inserts nums[i] into every possible position of the current permutation.
        *        This involves copying the current permutation, inserting nums[i] at a specific position,
        *        and then adding the new permutation to the result list.
        */
        List<List<Integer>> perms = helperRecursive(i + 1, nums);
        // Iterate over each permutation returned from the recursive call.
        for (List<Integer> p : perms) {
            // Insert nums[i] into every possible position of the current permutation p.
            for (int j = 0; j < p.size() + 1; j++) {
                List<Integer> pCopy = new ArrayList<>(); // Create a copy of the current permutation.
                pCopy.addAll(p); // Add all elements of the current permutation to the copy.
                pCopy.add(j, nums[i]); // Insert nums[i] at position j in the copy.
                resPerms.add(pCopy); // Add the new permutation to the result list.
            }
        }
        return resPerms;
    }

    // Time Complexity: O(n^2 * n!) where n is the number of elements in the input array.
    public static List<List<Integer>> permsIterative(int[] nums) {
        List<List<Integer>> perms = new ArrayList<>(); // Initialize the list to hold permutations.
        perms.add(new ArrayList<>()); // Start with an empty permutation.

        // Iterate over each number in the input array aiterates over each element of the input array,
        // and for each element, generate new perms by inserting the element into every possible 
        // position of the existing permutations.
        for (int n : nums) {
            List<List<Integer>> nextPerms = new ArrayList<>(); // Temporary list to hold the new permutations.
            // Iterate over each permutation in the current list of permutations.
            for (List<Integer> p : perms) {
                // Insert the current number into every possible position of the current permutation.
                for (int i = 0; i < p.size() + 1; i++) { 
                    List<Integer> pCopy = new ArrayList<>(); // Create a new list (copy) to avoid modifying the original permutation.
                    pCopy.addAll(p); // Add all elements of the current permutation to the copy.
                    pCopy.add(i, n); // Insert the current number at position i in the copy.
                    nextPerms.add(pCopy); // Add the new permutation to the temporary list.
                }
            }
            perms = nextPerms; // Update the list of permutations with the new permutations.
        }
        return perms;
    }
}