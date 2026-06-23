/**
 * Java Foundational Exercise - Day 17 (2026-06-23)
 * Topic: Nested Conditionals for Maximum Selection
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex017_Largest_Of_Three_Numbers {
    public static void main(String[] args) {
        int a = 25, b = 78, c = 52;
        int max = (a >= b && a >= c) ? a : (b >= c ? b : c);
        System.out.println("Largest: " + max);
    }
}
