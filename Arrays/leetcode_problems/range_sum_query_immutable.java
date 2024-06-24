// https://leetcode.com/problems/range-sum-query-immutable/

class NumArray {

    public int[] sumArr;

    public NumArray(int[] nums) {
        // Copy nums to the sum array to save space
        sumArr = nums;

        // The first value stays the same, then we add the next values
        // in a running sum
        for (int i = 1; i < nums.length; i++) {
            sumArr[i] += sumArr[i - 1];
        }
    }
    
    public int sumRange(int left, int right) {
        // We don't want to go out of bounds
        if (left == 0) {
            return sumArr[right];
        }

        return sumArr[right] - sumArr[left - 1];        
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */