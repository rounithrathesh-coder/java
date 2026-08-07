/**
 * Java Foundational Exercise - Day 62 (2026-08-07)
 * Topic: Minimum Element Extraction and In-Place Sorting
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex062_Selection_Sort_Algorithm {
    public static void main(String[] args) {
        int[] arr = {29, 10, 14, 37, 13};
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int t = arr[minIdx]; arr[minIdx] = arr[i]; arr[i] = t;
        }
        System.out.println("Sorted: " + java.util.Arrays.toString(arr));
    }
}
