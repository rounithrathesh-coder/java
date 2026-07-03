/**
 * Java Foundational Exercise - Day 27 (2026-07-03)
 * Topic: 2D Cartesian Coordinate Mapping
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex027_Coordinate_Quadrant_Finder {
    public static void main(String[] args) {
        int x = -4, y = 7;
        if (x > 0 && y > 0) System.out.println("Quadrant I");
        else if (x < 0 && y > 0) System.out.println("Quadrant II");
        else if (x < 0 && y < 0) System.out.println("Quadrant III");
        else System.out.println("Quadrant IV");
    }
}
