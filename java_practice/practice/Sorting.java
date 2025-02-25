package java_practice.practice;

import java.util.ArrayList;
import java.util.List;

public class Sorting {

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        printArray(arr);
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int low = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[low]) {
                    low = j;
                }
            }
            if (low != i) {
                swap(arr, low, i);
            }
        }

        printArray(arr);
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && current < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
        printArray(arr);
    }

    private static void merge(int[] arr, int st, int mid, int end) {
        List<Integer> temp = new ArrayList<Integer>();

        int i = st, j = mid + 1;

        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                temp.add(arr[i]);
                i++;
            } else {
                temp.add(arr[j]);
                j++;
            }
        }
        while (i <= mid) {
            temp.add(arr[i]);
            i++;
        }
        while (j <= end) {
            temp.add(arr[j]);
            j++;
        }

        for (int idx = 0; idx < temp.size(); idx++) {
            arr[st + idx] = temp.get(idx);
        }

    }

    public static void mergeSort(int[] arr, int st, int end) {
        if (st < end) {
            int mid = st + (end - st) / 2;

            mergeSort(arr, st, mid);
            mergeSort(arr, mid + 1, end);

            merge(arr, st, mid, end);
        }
    }

    private static int partition(int[] arr, int st, int end) {
        int pivot = arr[end];
        int idx = st - 1;
        for (int j = st; j < end; j++) {
            if (arr[j] < pivot) {
                idx++;
                swap(arr, j, idx);
            }
        }
        idx++;
        swap(arr, idx, end);
        return idx;
    }

    public static void quickSort(int[] arr, int st, int end) {
        if (st < end) {
            int pivIdx = partition(arr, st, end);
            quickSort(arr, st, pivIdx - 1);
            quickSort(arr, pivIdx + 1, end);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, n, largest);
        }
    }

    static void heapSort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {

            // Move current root to end
            swap(arr, 0, i);

            heapify(arr, i, 0);
        }
        printArray(arr);

    }

    public static void main(String[] args) {
        int arr[] = { 7, 8, 3, 4, 2, -1 };
        printArray(arr);

        System.out.println("After sorting");
        bubbleSort(arr);

        selectionSort(arr);

        insertionSort(arr);

        mergeSort(arr, 0, arr.length - 1);
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);
        printArray(arr);

        heapSort(arr);

    }
}
