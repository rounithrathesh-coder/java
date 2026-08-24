/**
 * Java Foundational Exercise - Day 79 (2026-08-24)
 * Topic: Abstract Method Contract and Implementation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex079_Abstract_Class_Geometric_Shape {
    static abstract class Shape { abstract double area(); }
    static class Circle extends Shape {
        double r;
        Circle(double r) { this.r = r; }
        @Override double area() { return Math.PI * r * r; }
    }
    public static void main(String[] args) {
        Shape s = new Circle(4.0);
        System.out.printf("Area: %.2f%n", s.area());
    }
}
