/**
 * Java Foundational Exercise - Day 55 (2026-07-31)
 * Topic: In-Place Array Compaction with Slow-Fast Pointers
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex055_Remove_Duplicates_Sorted_Array {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 4, 4, 5};
        int k = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[k - 1]) arr[k++] = arr[i];
        }
        System.out.println("Unique count: " + k);
    }
}
