// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

class Solution {
    
    // O(n^2)
    public int[] twoSumBruteForce(int[] numbers, int target) {
        int[] res = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    // Problem requires 1-based indices, so we add 1
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    // In this case we use the fact that the input is sorted
    // If the sum is too large, we can decrement out R pointer and if the 
    // sum is too small, we can increment our L pointer.
    // O(n)
    public int[] twoSumTwoPointers(int[] numbers, int target) {
        int L = 0;
        int R = numbers.length -1;

        while (L < R) {
            if (numbers[L] + numbers[R] < target) {
                L++;
            }
            else if (numbers[L] + numbers[R] > target) {
                R--;
            }
            else {
                // We have found a match
                return new int[] {L+ 1, R + 1};
            }
        }
        return null;
    }
}