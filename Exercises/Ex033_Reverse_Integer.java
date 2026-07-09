/**
 * Java Foundational Exercise - Day 33 (2026-07-09)
 * Topic: Digit Extraction and Place-Value Accumulation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex033_Reverse_Integer {
    public static void main(String[] args) {
        int n = 12345, rev = 0;
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        System.out.println("Reversed: " + rev);
    }
}
