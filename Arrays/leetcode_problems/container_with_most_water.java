// https://leetcode.com/problems/container-with-most-water/description/

class Solution {

    // Time: O(n^2), Space: O(1)
    // Gets a TLE error
    public int maxAreaBruteForce(int[] height) {
        int maxArea = 0;
        
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int distance = j - i;
                int h = Math.min(height[i], height[j]);
                int area = distance * h;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea; 
    }

    /* By always moving the pointer pointing to the shorter line, the algorithm 
        effectively narrows down the potential maximum area. 
        This is because the height of the container is constrained by 
        the shorter line, and moving the taller line inward won't increase the 
        area; hence, the shorter line is moved in the hope of finding a 
        taller line that could potentially form a larger area.

    Time: O(n), Space: O(1)
    */
    public int maxArea(int[] height) {
        int L = 0;
        int R = height.length - 1;
        int maxArea = 0;

        while (L < R) {
            int h = Math.min(height[L], height[R]);
            int w = R - L;
            int curArea = h * w;
            maxArea = Math.max(curArea, maxArea);

            if (height[L] < height[R]) {
                // M the shorter line inward might potentially find a taller line 
                // that could form a larger area.
                L++;
            }
            else {
                // Similarly, moving the shorter line inward might find a taller line.
                R--;
            }
        }
        return maxArea;
    }
}