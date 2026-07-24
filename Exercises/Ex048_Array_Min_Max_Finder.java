/**
 * Java Foundational Exercise - Day 48 (2026-07-24)
 * Topic: Tracking Minimum and Maximum in Single Scan
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex048_Array_Min_Max_Finder {
    public static void main(String[] args) {
        int[] arr = {23, 7, 89, 42, 3, 67};
        int min = arr[0], max = arr[0];
        for (int x : arr) {
            if (x < min) min = x;
            if (x > max) max = x;
        }
        System.out.println("Min: " + min + ", Max: " + max);
    }
}
