/**
 * Java Foundational Exercise - Day 30 (2026-07-06)
 * Topic: Factorial Iteration with Long Types
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex030_Factorial_Calculation {
    public static void main(String[] args) {
        int n = 10;
        long fact = 1;
        for (int i = 1; i <= n; i++) fact *= i;
        System.out.println("10! = " + fact);
    }
}
