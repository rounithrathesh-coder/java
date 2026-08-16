/**
 * Java Foundational Exercise - Day 71 (2026-08-16)
 * Topic: Recursive Call Stack and Base Case Handling
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex071_Recursion_Factorial_And_Fibonacci {
    public static long factorial(int n) {
        return (n <= 1) ? 1 : n * factorial(n - 1);
    }
    public static void main(String[] args) {
        System.out.println("5! = " + factorial(5));
    }
}
