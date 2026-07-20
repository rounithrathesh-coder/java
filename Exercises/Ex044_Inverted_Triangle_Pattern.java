/**
 * Java Foundational Exercise - Day 44 (2026-07-20)
 * Topic: Decreasing Loop Bounds Pattern
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex044_Inverted_Triangle_Pattern {
    public static void main(String[] args) {
        int rows = 5;
        for (int i = rows; i >= 1; i--) {
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
    }
}
