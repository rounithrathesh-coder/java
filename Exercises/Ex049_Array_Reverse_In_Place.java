/**
 * Java Foundational Exercise - Day 49 (2026-07-25)
 * Topic: Two-Pointer In-Place Array Reversal
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex049_Array_Reverse_In_Place {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int t = arr[l]; arr[l] = arr[r]; arr[r] = t;
            l++; r--;
        }
        System.out.println("Reversed: " + java.util.Arrays.toString(arr));
    }
}
