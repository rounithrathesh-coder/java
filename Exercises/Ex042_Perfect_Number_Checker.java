/**
 * Java Foundational Exercise - Day 42 (2026-07-18)
 * Topic: Proper Divisor Summation and Equality
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex042_Perfect_Number_Checker {
    public static void main(String[] args) {
        int num = 28, sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) sum += i;
        }
        System.out.println(num + " is perfect number: " + (sum == num));
    }
}
