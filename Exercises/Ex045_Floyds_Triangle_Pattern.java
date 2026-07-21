/**
 * Java Foundational Exercise - Day 45 (2026-07-21)
 * Topic: Consecutive Integer Triangle Filling
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex045_Floyds_Triangle_Pattern {
    public static void main(String[] args) {
        int n = 4, val = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) System.out.print(val++ + " ");
            System.out.println();
        }
    }
}
