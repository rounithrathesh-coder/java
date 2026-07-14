/**
 * Java Foundational Exercise - Day 38 (2026-07-14)
 * Topic: Euclidean Greatest Common Divisor Algorithm
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex038_GCD_And_LCM_Euclidean {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static void main(String[] args) {
        int a = 48, b = 18;
        int g = gcd(a, b);
        int lcm = (a * b) / g;
        System.out.println("GCD: " + g + ", LCM: " + lcm);
    }
}
