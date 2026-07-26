/**
 * Java Foundational Exercise - Day 50 (2026-07-26)
 * Topic: Linear Scanning with Early Return
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex050_Linear_Search_Algorithm {
    public static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {10, 25, 47, 89, 32};
        System.out.println("Found 89 at index: " + search(arr, 89));
    }
}
