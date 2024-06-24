// https://leetcode.com/problems/range-sum-query-2d-immutable/

class NumMatrix {

    // Pre-process sum matrix so lookups can be done in constant time
    public int[][] prefixSum;
    public NumMatrix(int[][] matrix) {
        int rowDim = matrix.length;
        int colDim = matrix[0].length;
        
        // Initialize the prefixSum matrix with an extra row and column filled with zeros
        // We do this so we avoid going out-of-bounds
        prefixSum = new int[rowDim + 1][colDim + 1];

        for (int i = 1; i <= rowDim; i++) {
            for (int j = 1; j <= colDim; j++) {
                // prefixSum at (i, j) is the sum of all elements in the 
                // submatrix from (0, 0) to (i-1, j-1)
            prefixSum[i][j] = matrix[i - 1][j - 1] // Current element in the original matrix
                            + prefixSum[i - 1][j] // Sum of elements above the current element
                            + prefixSum[i][j - 1] // Sum of elements to the left of the current element
                            - prefixSum[i - 1][j - 1]; // Remove the overlapping region that was added twice
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // Adjust for the extra row and column in the prefixSum matrix
        row1++;
        col1++;
        row2++;
        col2++;

        // Calculate the result using the inclusion-exclusion principle
        int res = prefixSum[row2][col2] // sum of the entire submatrix from (0,0) to (row2-1, col2-1)
                    - prefixSum[row2][col1 - 1] // subtract sum of the elements to the left of the desired submatrix
                    - prefixSum[row1 - 1][col2] // subtract the sum of the elements above the desired submatrix
                    + prefixSum[row1 - 1][col1 - 1];  // add back the sum of the overlapping region that was subtracted twice in the previous two steps

        return res;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
