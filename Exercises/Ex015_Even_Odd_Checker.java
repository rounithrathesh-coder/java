/**
 * Java Foundational Exercise - Day 15 (2026-06-21)
 * Topic: Modulo and Bitwise Parity Determination
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex015_Even_Odd_Checker {
    public static void main(String[] args) {
        int n = 42;
        boolean isEven = (n & 1) == 0;
        System.out.println(n + " is " + (isEven ? "Even" : "Odd"));
    }
}
