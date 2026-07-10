/**
 * Java Foundational Exercise - Day 34 (2026-07-10)
 * Topic: Integer Palindrome Invariant Check
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex034_Palindrome_Number_Checker {
    public static void main(String[] args) {
        int original = 12321, n = original, rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        System.out.println(original + " is palindrome: " + (original == rev));
    }
}
