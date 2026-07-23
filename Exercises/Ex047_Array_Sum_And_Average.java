/**
 * Java Foundational Exercise - Day 47 (2026-07-23)
 * Topic: Single-Pass Traversal and Statistical Metrics
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex047_Array_Sum_And_Average {
    public static void main(String[] args) {
        int[] arr = {12, 45, 67, 89, 34};
        int sum = 0;
        for (int x : arr) sum += x;
        double avg = (double) sum / arr.length;
        System.out.printf("Sum: %d, Average: %.2f%n", sum, avg);
    }
}
