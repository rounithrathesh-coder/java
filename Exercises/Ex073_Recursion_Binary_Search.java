/**
 * Java Foundational Exercise - Day 73 (2026-08-18)
 * Topic: Recursive Divide-and-Conquer Array Searching
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex073_Recursion_Binary_Search {
    public static int recBinarySearch(int[] arr, int l, int r, int target) {
        if (l > r) return -1;
        int mid = l + (r - l) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] > target) return recBinarySearch(arr, l, mid - 1, target);
        return recBinarySearch(arr, mid + 1, r, target);
    }
    public static void main(String[] args) {
        int[] arr = {11, 22, 33, 44, 55};
        System.out.println("Found 33 at: " + recBinarySearch(arr, 0, arr.length - 1, 33));
    }
}
