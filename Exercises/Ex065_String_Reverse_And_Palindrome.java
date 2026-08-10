/**
 * Java Foundational Exercise - Day 65 (2026-08-10)
 * Topic: Two-Pointer String Palindrome Validation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex065_String_Reverse_And_Palindrome {
    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println("racecar is palindrome: " + isPalindrome("racecar"));
    }
}
