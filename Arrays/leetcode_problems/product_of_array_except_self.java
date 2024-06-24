// https://leetcode.com/problems/product-of-array-except-self/description/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        // Create an array to store prefix products (products of all elements to the left of i)
        int[] pre = new int[n];
        pre[0] = 1; // Initialize the first element to 1 since there are no elements to the left of the first element

        // Create an array to store suffix products (products of all elements to the right of i)
        int[] suff = new int[n];
        suff[n - 1] = 1; // Initialize the last element to 1 since there are no elements to the right of the last element

        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1]; // Multiply the previous prefix product by the previous element in the array
        }

        for (int i = n - 2; i >= 0; i--) {
            suff[i] = suff[i + 1] * nums[i + 1]; // Multiply the next suffix product by the next element in the array
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = pre[i] * suff[i]; // Multiply the corresponding prefix and suffix products to get the result for each element
        }
        
        return answer;
    }
}

