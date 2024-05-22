public class InsertionSort {
    public static int[] insertionSort(int[] arr) {

        // Start from the second element because the first element
        // is already sorted
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            // If we haven't reached the front of the sorted array
            // and the element to the left is greater than the current element
            // swap the elements
            while (j >= 0 && arr[j + 1] < arr[j]) {
                // Store the current element temporarily
                int tmp = arr[j + 1];
                // Move the larger element to the right
                arr[j + 1] = arr[j];
                // Move the smaller element to the left
                arr[j] = tmp;
                // Move backwards through the array towards
                // the front of the sorted array
                j--;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        System.out.println("Original array:");
        printArray(arr);

        insertionSort(arr);
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