// https://leetcode.com/problems/longest-turbulent-subarray/

/*
The sequence must alternate such that if one element is greater 
than its neighboring elements, the next element should be less 
than its neighboring elements, and vice versa. In other words, the 
greater than/less than signs should alternate.

The conditions in the example are for indices, not values in the array.

Return the length of the max subarray.
*/

class Solution {
    public int maxTurbulenceSize(int[] arr) {
        // A single element is considered turbulent
        if (arr.length <= 1) {
            return arr.length;
        }

        // Length of the longest turbulent subarrays starting from odd/even indices
        int maxOddStart = 1;
        int maxEvenStart = 1;

        // Length of the current turbulent subarrays starting from odd/even indices
        int curOddStart = 1;
        int curEvenStart = 1;

        // We start the loop at the second element to always compare with the previous element
        for (int i = 1; i < arr.length; i++) {
            // If the current index is even
            if (i % 2 == 0) {
                // If the previous element > current, we continue the odd-start pattern, else reset
                curOddStart = arr[i - 1] > arr[i] ? curOddStart + 1 : 1;
                // If the previous element < current, we continue the even-start pattern, else reset
                curEvenStart = arr[i - 1] < arr[i] ? curEvenStart + 1 : 1;
            } else {
                // If the current index is odd
                // If the previous element < current, we continue the odd-start pattern, else reset
                curOddStart = arr[i - 1] < arr[i] ? curOddStart + 1 : 1;
                // If the previous element > current, we continue the even-start pattern, else reset
                curEvenStart = arr[i - 1] > arr[i] ? curEvenStart + 1 : 1;
            }

            // Update the max lengths of turbulent subarrays
            maxOddStart = Math.max(maxOddStart, curOddStart);
            maxEvenStart = Math.max(maxEvenStart, curEvenStart);
        }

        // Return the length of the longest turbulent subarray
        return Math.max(maxOddStart, maxEvenStart);
    }
}