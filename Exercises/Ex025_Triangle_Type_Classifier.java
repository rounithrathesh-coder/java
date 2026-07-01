/**
 * Java Foundational Exercise - Day 25 (2026-07-01)
 * Topic: Geometric Side Length Validation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex025_Triangle_Type_Classifier {
    public static void main(String[] args) {
        int a = 5, b = 5, c = 5;
        if (a == b && b == c) System.out.println("Equilateral");
        else if (a == b || b == c || a == c) System.out.println("Isosceles");
        else System.out.println("Scalene");
    }
}
