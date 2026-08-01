/**
 * Java Foundational Exercise - Day 56 (2026-08-01)
 * Topic: Triple Reversal Array Rotation Algorithm
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex056_Rotate_Array_By_K_Positions {
    private static void reverse(int[] a, int l, int r) {
        while (l < r) { int t = a[l]; a[l++] = a[r]; a[r--] = t; }
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3 % arr.length;
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        System.out.println("Rotated by 3: " + java.util.Arrays.toString(arr));
    }
}
