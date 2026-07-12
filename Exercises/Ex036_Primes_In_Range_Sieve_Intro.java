/**
 * Java Foundational Exercise - Day 36 (2026-07-12)
 * Topic: Generating Primes in a Range
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex036_Primes_In_Range_Sieve_Intro {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 2; i <= 50; i++) {
            boolean prime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) { prime = false; break; }
            }
            if (prime) count++;
        }
        System.out.println("Primes under 50: " + count);
    }
}
