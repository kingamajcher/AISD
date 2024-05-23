import java.util.*;

public class DualPivot {
    static int comparisonCount = 0;
    static int swapCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter integers separated by spaces:");
        String input = scanner.nextLine();
        String[] tokens = input.split("\\s+");
        int[] data = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            data[i] = Integer.parseInt(tokens[i]);
        }

        int n = data.length;

        System.out.print("Original array: ");
        printArray(data);
        System.out.println();

        int[] originalData = Arrays.copyOf(data, data.length);

        long startTime = System.nanoTime();
        dualPivotQuickSort(data, 0, n - 1);
        long endTime = System.nanoTime();
        long time = endTime - startTime;

        System.out.println();
        System.out.print("Original array: ");
        printArray(originalData);
        System.out.print("Sorted array:   ");
        printArray(data);
        System.out.println();

        if (isSorted(data)) {
            System.out.println("The array is sorted correctly.");
        } else {
            System.out.println("Error: The array is not sorted correctly!");
        }

        System.out.println("Swaps: " + swapCount);
        System.out.println("Comparisons: " + comparisonCount);
        System.out.println("Time: " + time);
    }

    static void printArray(int[] arr) {
        if (arr.length < 40) {
            for (int key : arr) {
                System.out.printf("%2d ", key);
            }
            System.out.println();
        }
    }

    static boolean compareKeys(int a, int b) {
        comparisonCount++;
        return a < b;
    }

    static void swapKeys(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
        swapCount++;
    }

    static int partition(int[] arr, int low, int high, int[] lp) {
        if (arr[low] > arr[high]) {
            swapKeys(arr, low, high);
        }

        int p = arr[low]; // pivot
        int q = arr[high]; // pivot
        int i = low + 1;
        int k = low + 1;
        int g = high - 1;
        int l = 0; // counter
        int s = 0; // counter
        while (k <= g) {
            if (l > s) {
                if (compareKeys(q, arr[k])) {
                    while (compareKeys(q, arr[g]) && k < g)
                        g--;
                    swapKeys(arr, k, g);
                    l++;
                    g--;
                    if (compareKeys(arr[k], p)) {
                        swapKeys(arr, k, i);
                        i++;
                        s++;
                    }
                } else if (compareKeys(arr[k], p)) {
                    swapKeys(arr, k, i);
                    s++;
                    i++;
                }
            }
            else {
                if (compareKeys(arr[k], p)) {
                    swapKeys(arr, k, i);
                    s++;
                    i++;
                } else if (compareKeys(q, arr[k])) {
                    while (compareKeys(q, arr[g]) && k < g)
                        g--;
                    swapKeys(arr, k, g);
                    l++;
                    g--;
                    if (compareKeys(arr[k], p)) {
                        swapKeys(arr, k, i);
                        i++;
                        s++;
                    }
                }
            }
            k++;
        }
        i--;
        g++;
        if (low != i) {
            swapKeys(arr, low, i);
        }
        if (high != g) {
            swapKeys(arr, high, g);
        }
        lp[0] = i;
        printArray(arr);
        return g;
    }

    static void dualPivotQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int[] lp = new int[1];
            int rp = partition(arr, low, high, lp);
            dualPivotQuickSort(arr, low, lp[0] - 1);
            dualPivotQuickSort(arr, lp[0] + 1, rp - 1);
            dualPivotQuickSort(arr, rp + 1, high);
        }
    }

    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
