/**
 * Java Foundational Exercise - Day 53 (2026-07-29)
 * Topic: Single-Pass Tracking of Top Two Elements
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex053_Second_Largest_Element {
    public static void main(String[] args) {
        int[] arr = {12, 35, 1, 10, 34, 1};
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int x : arr) {
            if (x > first) { second = first; first = x; }
            else if (x > second && x != first) second = x;
        }
        System.out.println("Second largest: " + second);
    }
}
