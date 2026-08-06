/**
 * Java Foundational Exercise - Day 61 (2026-08-06)
 * Topic: Adjacent Element Swapping with Early Exit
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex061_Bubble_Sort_Algorithm {
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        System.out.println("Sorted: " + java.util.Arrays.toString(arr));
    }
}
