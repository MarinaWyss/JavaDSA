public class BucketSort {
    public static int[] bucketSort(int[] arr, int maxVal) {
        // Initialize buckets array to count occurrences
        int[] buckets = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            buckets[i] = 0;
        }

        // Iterate through input array to count the number of times each value occurs
        for (int num : arr) {
            buckets[num]++;
        }

        // Fill each bucket in the original array
        int i = 0; // Pointer to keep track of next insertion point for arr
        for (int n = 0; n <= maxVal; n++) {
            // n keeps track of current position in the buckets
            while (buckets[n] > 0) {
                arr[i] = n;
                i++;
                buckets[n]--;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        System.out.println("Original array:");
        printArray(arr);

        bucketSort(arr, 9); // 9 is the maximum value in the array
        System.out.println("Sorted array:");
        printArray(arr);
    }

    // Utility method to print an array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
