/**
 * Java Foundational Exercise - Day 07 (2026-06-13)
 * Topic: Type Conversion and Precision Preservation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex007_Type_Casting_Widening_Narrowing {
    public static void main(String[] args) {
        int count = 100;
        double widened = count;
        double pi = 3.14159;
        int narrowed = (int) pi;
        System.out.println("Widened: " + widened + ", Narrowed: " + narrowed);
    }
}
