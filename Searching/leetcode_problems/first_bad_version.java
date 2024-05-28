// https://leetcode.com/problems/first-bad-version/description/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int first = 1;
        int last = n;

        while (first < last) {
            int mid = (first + last) / 2;

            boolean isBad = isBadVersion(mid);

            if (isBad == true) {
                // If this version is mad, no need to check any after it
                last = mid;
            }
            else {
                // If it is not bad, move one more version to the right
                first = mid + 1;
            }
        }
        // Once first >= last, we've narrowed the search range to a single
        // version, meaning we found the first bad version.
        return first;
    }
}