public class BinarySearchRange {
    public static int binarySearchRange(int low, int high) {

        while (low <= high) {
            int mid = (low + high) / 2;

            if (isCorrect(mid) < 0) {
                // If the guess is too low, move to the right
                low = mid + 1;
            }
            else if (isCorrect(mid) > 0) {
                // If the guess is too high, move to the left
                high = mid - 1;
            }
            else {
                // If the target wasn't greater or smaller,
                // then we must have found it, so return this index.
                return mid;
            }
        }
        return -1;  // If the target isn't found
    }

    public static int isCorrect(int n) {
        // The logic in this function could be anything

        // In this case, we're just going to look for 10
        int target = 10;

        if (n > target) {
            return 1;
        }
        else if (n < target) {
            return -1;
        }
        else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int low = 0;
        int high = 100;
        int result = binarySearchRange(low, high);
        System.out.println("Target found: " + result);
    }
}