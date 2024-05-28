public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (target > arr[mid]) {
                left = mid + 1;
            }
            else if (target < arr[mid]) {
                right = mid - 1;
            }
            else {
                // If the target wasn't greater or smaller,
                // then we must have found it, so return this index.
                return mid;
            }
        }
        return -1;  // If the target isn't found
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 7;
        int result = binarySearch(arr, target);
        System.out.println("Target " + target + " found at index " + result);

        int targetNotThere = 2;
        int resultNotThere = binarySearch(arr, targetNotThere);
        System.out.println("Target " + targetNotThere + " found at index " + resultNotThere);
    }
}