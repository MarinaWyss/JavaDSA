// https://leetcode.com/problems/search-a-2d-matrix/description/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Make sure the target is within the range of the entire
        // matrix
        int m = matrix.length;
        int n = matrix[0].length;
    
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }

        // Iterate through each row of the matrix
        for (int[] row : matrix) {
            int left = 0;
            int right = row.length - 1;
            // If the target is within the range of the row
            if (target <= row[right] && target >= row[left]) {
                // do binary search
                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (target > row[mid]) {
                        left = mid + 1;
                    }
                    else if (target < row[mid]) {
                        right = mid - 1;
                    }
                    else {
                        return true;
                    }
                }
            } 
        }
        // If we go through every row and don't find the target
        // return false
        return false;
    }
}