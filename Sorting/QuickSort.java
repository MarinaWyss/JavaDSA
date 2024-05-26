public class QuickSort {
    public static int[] quickSort(int[] arr, int startIndex, int endIndex) {

        // Check if we've reached the base case where the arr.length == 1
        if (endIndex - startIndex + 1 <= 1) {
            return arr;
        }

        // Use the last index as the pivot
        int pivot = arr[endIndex];
        int left = startIndex;

        // Partition elements smaller than pivot on the left side
        for (int i = startIndex; i < endIndex; i++) {
            if (arr[i] < pivot) {
                // Swap
                int tmp = arr[left];
                arr[left] = arr[i];
                arr[i] = tmp;
                left++;
            }
        }

        // Move pivot between left and right sides
        arr[endIndex] = arr[left];
        arr[left] = pivot;

        // Recursively run quickSort on left and right portions
        quickSort(arr, startIndex, left-1); // left-1 because left is already in the right spot
        quickSort(arr, left+1, endIndex); // left+1 for the same reason

        return arr;
    }

    public static void main(String[] args) {
        // Test input array
        int[] arr = {12, 11, 13, 5, 6, 7};

        System.out.println("Original array:");
        printArray(arr);

        // Sort the array using quick sort
        QuickSort.quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        printArray(arr);
    }

    // Helper method to print an array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}