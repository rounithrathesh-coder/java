/**
 * Java Foundational Exercise - Day 32 (2026-07-08)
 * Topic: Fibonacci Sequence Generation with Two Pointers
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex032_Fibonacci_Series_Iterative {
    public static void main(String[] args) {
        int n = 10;
        long a = 0, b = 1;
        System.out.print("Fibonacci: " + a + " " + b);
        for (int i = 2; i < n; i++) {
            long c = a + b;
            System.out.print(" " + c);
            a = b;
            b = c;
        }
        System.out.println();
    }
}
