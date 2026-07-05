/**
 * Java Foundational Exercise - Day 29 (2026-07-05)
 * Topic: Iterative Accumulator vs Closed Form
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex029_Sum_Of_N_Natural_Numbers {
    public static void main(String[] args) {
        int n = 100;
        long sum = 0;
        for (int i = 1; i <= n; i++) sum += i;
        System.out.println("Sum 1..100: " + sum);
    }
}
