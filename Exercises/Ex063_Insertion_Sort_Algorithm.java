/**
 * Java Foundational Exercise - Day 63 (2026-08-08)
 * Topic: Subarray Insertion Sorting Invariant
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex063_Insertion_Sort_Algorithm {
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) { arr[j + 1] = arr[j]; j--; }
            arr[j + 1] = key;
        }
        System.out.println("Sorted: " + java.util.Arrays.toString(arr));
    }
}
