/**
 * Java Foundational Exercise - Day 31 (2026-07-07)
 * Topic: Formatted Tabular Output Generation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex031_Multiplication_Table {
    public static void main(String[] args) {
        int num = 7;
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d x %2d = %2d%n", num, i, num * i);
        }
    }
}
