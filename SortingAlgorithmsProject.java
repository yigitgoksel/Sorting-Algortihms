import java.io.*;
import java.util.*;

import java.util.Arrays;

public class SortingAlgorithmsProject {

    static int insertionCount = 0;

    // insertion Baþlangýç
    static void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            insertionCount++;
            /*
             * Move elements of arr[0..i-1], that are greater than key, to one position
             * ahead of their current position
             */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
                insertionCount++;
            }
            arr[j + 1] = key;
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    static int binarysearchCount = 0;
    static int binaryinsertionCount;

    // binaryinsertionBaþlangýç
    public static void binaryInsertionSort(int[] nums) {
        int length = nums.length;

        for (int i = 1; i < length; ++i) {
            int key = nums[i];
            int insertedPosition = findPosition(nums, 0, i - 1, key);

            for (int j = i - 1; j >= insertedPosition; --j) {
                binaryinsertionCount++;
                nums[j + 1] = nums[j];
            }

            nums[insertedPosition] = key;
        }
    }

    public static int findPosition(int[] nums, int start, int end, int key) {
        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (key < nums[mid]) {
                end = mid - 1;
                binarysearchCount++;
            } else {
                start = mid + 1;
                binarysearchCount++;
            }
        }

        return start;
    }

    public static int binaryInsertionComparison(int a, int b) {
        if (a > b) {
            return a;
        } else
            return b;
    }

    static int mergeCount = 0;

    // merge Baþlangýç
    static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /* Copy data to temp arrays */
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
                mergeCount++;
            } else {
                arr[k] = R[j];
                j++;
                mergeCount++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void sort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    // quickSortBaþlangýç
    static int quicksortCount = 0;

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * This function takes last element as pivot, places the pivot element at its
     * correct position in sorted array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     */
    static int partition(int[] arr, int low, int high) {

        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            quicksortCount++;
            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /*
     * The main function that implements QuickSort arr[] --> Array to be sorted, low
     * --> Starting index, high --> Ending index
     */
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Function to print an array
    static void printArray(int[] arr, int size) {
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    // quicksort3median
    static int medianCount = 0;

    public static int medianPivot(int arr[], int low, int high) {
        /*
         * create subarray with low, high, and middle elements in the array sort the
         * subarray and use index 1 as the median of 3
         */

        int first = arr[low];
        int last = arr[arr.length - 1];
        int mid = (high) / 2;

        int[] sortingArr = { arr[low], arr[mid], arr[high] };

        Arrays.sort(sortingArr);

        int middleValue = sortingArr[1];

        // printArray(sortingArr);

        // swap with the last to serve as pivot
        int temp = arr[high];
        arr[high] = middleValue;
        if (middleValue == arr[low]) {
            arr[low] = temp;
        } else if (middleValue == arr[mid]) {
            arr[mid] = temp;
        }

        // System.out.println("median: ");

        return medianPartition(arr, low, high);

    }

    // ----------------------------------------------------------------------
    /* method for medianQuicksort */
    public static void medianQuickSort(int arr[], int low, int high) {
        if (low >= high)
            return;

        if (low < high) {

            int pi = medianPivot(arr, low, high);

            QuickSort(arr, low, high);

        }
    }

    // -----------------------------------------------------------------------
    public static void QuickSort(int arr[], int low, int high) {

        if (low < high) {
            int pi = medianPartition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            QuickSort(arr, low, pi - 1);
            QuickSort(arr, pi + 1, high);
        }
    }

    // ----------------------------------------------------------------------

    // -----------------------------------------------------------------------
    public static int medianPartition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            medianCount++;
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

            }

        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;

    }

    static int heapCount = 0;

    // heapSort Baþlangýç
    public static void heapSort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    static void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root

        heapCount += 2;
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    // countingSortBaþlangýç
    int counter;
    static void countSort (int arr[],int size,int counts[]){
       for(int i=0; i<size; i++){
            counts[arr[i]]++;
       }
       int j =0;
       for(int i=0; i<size; i++){
       if (j<10001) {
       while(counts[j]==0){
            j++;
       }
       if (j>10001) {
         arr[i] = j;
         j++;
       }   
    }
       }
    }
   

    public static int[] copyArray;

    public static void tempArray(int arr[]) {
        copyArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArray[i] = arr[i];
        }

    }

    public static int[] reverseArray;

    public static void tempArrayReverse(int arr[]) {
        reverseArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < arr.length; j++)
                reverseArray[i] = arr[j];

        }

    }

    static void reverse(int a[], int n) {
        int i, k, t;
        for (i = 0; i < n / 2; i++) {
            t = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = t;
        }

        /* printing the reversed array */

    }

    public static void main(String[] args) {
        // int arr[] = {-3, 9, 6, 11, 4};
        int n = 100;
        int arr[] = new int[n];
        int x =10001;
        int counts[]= new int [x];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100);
        }

        int duplicateArr[] = new int[n];
        for (int i = 0; i < duplicateArr.length; i++) {
            duplicateArr[i] = 1;
        }

        tempArray(arr);
        // merge sort
        System.out.println("Merge Sort average case");
        sort(copyArray, 0, copyArray.length - 1);
        System.out.println(mergeCount);
        mergeCount = 0;
        System.out.println("Merge Sort sorted case");
        sort(copyArray, 0, copyArray.length - 1);
        System.out.println(mergeCount);
        mergeCount = 0;
        System.out.println("Merge Sort reverse sorted case");
        reverse(copyArray, copyArray.length);

        sort(copyArray, 0, copyArray.length - 1);
        System.out.println(mergeCount);
        mergeCount = 0;
        System.out.println("Merge Sort duplicated case");
        sort(duplicateArr, 0, duplicateArr.length - 1);
        System.out.println(mergeCount);
        System.out.println();
        tempArray(arr);

        // quicksort
        System.out.println("Quick Sort average case");
        quickSort(copyArray, 0, copyArray.length - 1);
        System.out.println(quicksortCount);
        quicksortCount = 0;
        System.out.println("Quick Sort sorted case");
        quickSort(copyArray, 0, copyArray.length - 1);
        System.out.println(quicksortCount);
        quicksortCount = 0;
        System.out.println("Quick Sort reverse sorted case");

        reverse(copyArray, copyArray.length);
        quickSort(copyArray, 0, copyArray.length - 1);
        System.out.println(quicksortCount);
        quicksortCount = 0;
        System.out.println("Quick Sort duplicated case");
        quickSort(duplicateArr, 0, duplicateArr.length - 1);
        System.out.println(quicksortCount);
        System.out.println();
        tempArray(arr);
      
        // insertionSort
        System.out.println("InsertionSort average case");
        insertionSort(copyArray);
        System.out.println(insertionCount);
        insertionCount = 0;
        System.out.println("Insertion Sort  sorted case");
        insertionSort(copyArray);
        System.out.println(insertionCount);
        insertionCount = 0;
        System.out.println("Insertion Sort reverse sorted case");

        reverse(copyArray, copyArray.length);

        insertionSort(copyArray);
        System.out.println(insertionCount);
        insertionCount = 0;
        System.out.println("Insertion Sort duplicated case");

        insertionSort(duplicateArr);

        System.out.println(insertionCount);
        System.out.println();
        tempArray(arr);

        // heap sort
        System.out.println("Heap Sort average case");
        heapSort(copyArray);
        System.out.println(heapCount);
        heapCount = 0;
        System.out.println("Heap Sort  sorted case");
        heapSort(copyArray);
        System.out.println(heapCount);
        heapCount = 0;
        System.out.println("Heap Sort  reverse sorted case");

        reverse(copyArray, copyArray.length);

        heapSort(copyArray);
        System.out.println(heapCount);
        heapCount = 0;
        System.out.println("Heap Sort duplicated case");

        heapSort(duplicateArr);

        System.out.println(heapCount);
        System.out.println();
        tempArray(arr);
        // binary insertion count
        System.out.println("Binary Insertion average case");
        binaryInsertionSort(copyArray);
        System.out.println(binaryInsertionComparison(binaryinsertionCount, binarysearchCount));
        binaryinsertionCount = 0;
        binarysearchCount = 0;
        System.out.println("Binary Insertion  sorted case");
        binaryInsertionSort(copyArray);
        System.out.println(binaryInsertionComparison(binaryinsertionCount, binarysearchCount));
        binaryinsertionCount = 0;
        binarysearchCount = 0;

        System.out.println("Binary Insertion  reverse sorted case");

        reverse(copyArray, copyArray.length);

        binaryInsertionSort(copyArray);

        System.out.println(binaryInsertionComparison(binaryinsertionCount, binarysearchCount));

        binaryinsertionCount = 0;

        binarysearchCount = 0;

        System.out.println("Binary Insertion duplicated case");

        binaryInsertionSort(duplicateArr);

        System.out.println(binaryInsertionComparison(binaryinsertionCount, binarysearchCount));
        System.out.println();
        tempArray(arr);

        // median
        System.out.println("Median Quicksort average case");
        medianQuickSort(copyArray, 0, copyArray.length - 1);
        System.out.println(medianCount);
        medianCount = 0;
        System.out.println("Median Quicksort sorted case");
        medianQuickSort(copyArray, 0, copyArray.length - 1);
        System.out.println(medianCount);
        medianCount = 0;
        System.out.println("Median Quicksort  reverse sorted case");
        reverse(copyArray, copyArray.length);
        medianQuickSort(copyArray, 0, copyArray.length - 1);
        System.out.println(medianCount);
        medianCount = 0;
        System.out.println("Median Quicksort duplicated case");
        medianQuickSort(duplicateArr, 0, duplicateArr.length - 1);
        System.out.println(medianCount);
        System.out.println();
        tempArray(arr);


        //countsort
        System.out.println("Counting Sort average case");
        countSort (copyArray,n,counts);
        System.out.println(n);
        medianCount = 0;
        System.out.println("Counting Sort sorted case");
        countSort (copyArray,n,counts);
        System.out.println(n);
        medianCount = 0;
        System.out.println("Counting Sort  reverse sorted case");
        reverse(copyArray, copyArray.length);
        countSort (copyArray,n,counts);
        System.out.println(n);
        medianCount = 0;
        System.out.println("Counting Sort duplicated case");
        countSort (duplicateArr,n,counts);
        System.out.println(n);
        System.out.println();
        tempArray(arr);   



    }

}
