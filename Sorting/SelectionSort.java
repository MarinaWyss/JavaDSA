public class SelectionSort {
    public static int[] selectionSort(int[] arr) {

        // Iterate through array until the second-to-last element
        // we don't need to look at the last element because once we get
        // there it will already be in its sorted position
        for (int i = 0; i < arr.length - 1; i++) {

            // Find the min element in the unsorted array
            int minIndex = i;
            // Loop through unsorted part of the array (i + 1)
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found min element with the first element
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        System.out.println("Original array:");
        printArray(arr);

        selectionSort(arr);
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