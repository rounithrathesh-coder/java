/**
 * Java Foundational Exercise - Day 11 (2026-06-17)
 * Topic: Geometric Area and Perimeter Calculations
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex011_Circle_And_Rectangle_Geometry {
    public static void main(String[] args) {
        double radius = 5.0, width = 8.0, height = 4.0;
        double circleArea = Math.PI * radius * radius;
        double rectPerimeter = 2 * (width + height);
        System.out.printf("Circle Area: %.2f, Rectangle Perimeter: %.2f%n", circleArea, rectPerimeter);
    }
}
