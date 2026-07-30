/**
 * Java Foundational Exercise - Day 54 (2026-07-30)
 * Topic: Monotonic Order Invariant Verification
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex054_Check_If_Array_Sorted {
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 19};
        System.out.println("Is sorted: " + isSorted(arr));
    }
}
