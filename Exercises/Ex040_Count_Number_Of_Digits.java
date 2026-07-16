/**
 * Java Foundational Exercise - Day 40 (2026-07-16)
 * Topic: Iterative and Logarithmic Digit Counting
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex040_Count_Number_Of_Digits {
    public static void main(String[] args) {
        int num = 456789;
        int count = (int) Math.log10(num) + 1;
        System.out.println("Digit count of " + num + ": " + count);
    }
}
