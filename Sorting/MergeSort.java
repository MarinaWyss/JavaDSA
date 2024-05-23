public class MergeSort {
    public static int[] mergeSort(int[] arr, int startIndex, int endIndex) {

        // Check if we've reached the base case where the arr.length == 1
        if (endIndex - startIndex + 1 <= 1) {
            return arr;
        }

        // midIndex is the mid point rounded down
        int midIndex = (startIndex + endIndex) / 2;
        // Merge left half
        mergeSort(arr, startIndex, midIndex);
        // Merge right half
        mergeSort(arr, midIndex + 1, endIndex);
        
        // Merge sorted left half of array with sorted right half
        merge(arr, startIndex, midIndex, endIndex);

        return arr;
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // Merges two subarrays of arr[].
        // First subarray is arr[left-mid]
        // Second subarray is arr[mid+1-right]

        // Find lengths of the two subarrays
        int lengthLeft = mid - left + 1;  // Add 1 to also grab the mid index
        int lengthRight = right - mid;

        // Create temp arrays
        int L[] = new int[lengthLeft];
        int R[] = new int[lengthRight];

        // Copy the sorted left and right halfs to temp arrays
        for (int i = 0; i < lengthLeft; i++) {
            L[i] = arr[left + i];
        }

        for (int j = 0; j < lengthRight; j++) {
            R[j] = arr[mid + 1 + j];
        }

        // Create indices for each of the sub arrays and the merged array
        int iLeft = 0;
        int iRight = 0;
        int iMerged = left;

        // Merge the two sorted halfs into the original array
        while (iLeft < lengthLeft && iRight < lengthRight) {
            if (L[iLeft] <= R[iRight]) {
                arr[iMerged] = L[iLeft];
                iLeft++;
            } 
            else {
                arr[iMerged] = R[iRight];
                iRight++;
            }
            iMerged++;
        }

        // Copy any remaining elements in the left or right temp arrays
        while (iLeft < lengthLeft) {
            arr[iMerged] = L[iLeft];
            iLeft++;
            iMerged++;
        }

        while (iRight < lengthRight) {
            arr[iMerged] = R[iRight];
            iRight++;
            iMerged++;
        }
    }

    public static void main(String[] args) {
        // Test input array
        int[] arr = {12, 11, 13, 5, 6, 7};

        System.out.println("Original array:");
        printArray(arr);

        // Sort the array using merge sort
        MergeSort.mergeSort(arr, 0, arr.length - 1);

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