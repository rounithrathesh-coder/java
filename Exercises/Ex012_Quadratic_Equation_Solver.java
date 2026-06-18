/**
 * Java Foundational Exercise - Day 12 (2026-06-18)
 * Topic: Solving Quadratic Roots using Discriminant
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex012_Quadratic_Equation_Solver {
    public static void main(String[] args) {
        double a = 1, b = -5, c = 6;
        double discriminant = b * b - 4 * a * c;
        if (discriminant >= 0) {
            double r1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double r2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Roots: " + r1 + " and " + r2);
        } else {
            System.out.println("Complex roots");
        }
    }
}
