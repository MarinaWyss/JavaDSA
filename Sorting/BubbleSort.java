public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        // Outer loop for traversing the array
        for (int i = 0; i < n - 1; i++) {
            
            // Inner loop to compare elements
            // We only go until we reach n - 1 - i, since i is the total number
            // of elements that have already been sorted at the end in the outer loop
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        System.out.println("Original array:");
        printArray(arr);

        bubbleSort(arr);
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