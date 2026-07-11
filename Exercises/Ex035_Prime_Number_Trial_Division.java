/**
 * Java Foundational Exercise - Day 35 (2026-07-11)
 * Topic: Square Root Primality Testing Invariant
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex035_Prime_Number_Trial_Division {
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int num = 97;
        System.out.println(num + " is prime: " + isPrime(num));
    }
}
