// https://leetcode.com/problems/counting-bits/description

class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            int count = 0;
            int num = i;
            while (num > 0) {
                // Bitwise AND n with 1 to see if they are both 1.
                if ((num & 1) == 1) count++;
                // Shift n to the right (same as / 2)
                num = num >> 1;
            }
            res[i] = count;
        }
        return res;
    }
}

/*
    Example: n = 5

    i = 0
    Binary: 0
    count = 0 (no 1s)
    res[0] = 0

    i = 1
    Binary: 1
    Steps:
        num = 1
        num & 1 = 1 (1 & 1 = 1), count = 1
        num = num >> 1 = 0 (1 >> 1 = 0), loop ends
        res[1] = 1

    i = 2
    Binary: 10
    Steps:
        num = 2
        num & 1 = 0 (10 & 1 = 0), count = 0
        num = num >> 1 = 1 (10 >> 1 = 1)
        num & 1 = 1 (1 & 1 = 1), count = 1
        num = num >> 1 = 0 (1 >> 1 = 0), loop ends
        res[2] = 1

    i = 3
    Binary: 11
    Steps:
        num = 3
        num & 1 = 1 (11 & 1 = 1), count = 1
        num = num >> 1 = 1 (11 >> 1 = 1)
        num & 1 = 1 (1 & 1 = 1), count = 2
        num = num >> 1 = 0 (1 >> 1 = 0), loop ends
        res[3] = 2

    i = 4
    Binary: 100
    Steps:
        num = 4
        num & 1 = 0 (100 & 1 = 0), count = 0
        num = num >> 1 = 2 (100 >> 1 = 10)
        num & 1 = 0 (10 & 1 = 0), count = 0
        num = num >> 1 = 1 (10 >> 1 = 1)
        num & 1 = 1 (1 & 1 = 1), count = 1
        num = num >> 1 = 0 (1 >> 1 = 0), loop ends
        res[4] = 1

    i = 5
    Binary: 101
    Steps:
        num = 5
        num & 1 = 1 (101 & 1 = 1), count = 1
        num = num >> 1 = 2 (101 >> 1 = 10)
        num & 1 = 0 (10 & 1 = 0), count = 1
        num = num >> 1 = 1 (10 >> 1 = 1)
        num & 1 = 1 (1 & 1 = 1), count = 2
        num = num >> 1 = 0 (1 >> 1 = 0), loop ends
        res[5] = 2

    Final Result:
    res = [0, 1, 1, 2, 1, 2]
*/