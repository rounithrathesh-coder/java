/**
 * Java Foundational Exercise - Day 41 (2026-07-17)
 * Topic: Binary Exponentiation Algorithm
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex041_Power_Calculation_Fast_Exponentiation {
    public static long power(long base, int exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res *= base;
            base *= base;
            exp >>= 1;
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println("2^10 = " + power(2, 10));
    }
}
