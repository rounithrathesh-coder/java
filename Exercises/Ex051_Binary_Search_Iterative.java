/**
 * Java Foundational Exercise - Day 51 (2026-07-27)
 * Topic: Logarithmic Divide-and-Conquer Search
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex051_Binary_Search_Iterative {
    public static int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] sorted = {10, 20, 30, 40, 50, 60};
        System.out.println("Found 40 at index: " + binarySearch(sorted, 40));
    }
}
