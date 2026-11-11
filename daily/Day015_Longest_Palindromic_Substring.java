/**
 * Daily Java Challenge - Day 015 (2026-11-11)
 * Topic: Dynamic Programming / String Algorithms - Longest Palindromic Substring
 * Category: Dynamic Programming
 * 
 * Problem Description:
 * Given a string s, return the longest palindromic substring in s.
 * 
 * Approaches:
 * 1. Expand Around Center (Implemented below):
 *    - Time Complexity: O(n^2) where n is string length.
 *    - Space Complexity: O(1) auxiliary space (optimal).
 * 2. Dynamic Programming:
 *    - Time Complexity: O(n^2)
 *    - Space Complexity: O(n^2) table.
 */

public class Day015_Longest_Palindromic_Substring {

    /**
     * Finds the longest palindromic substring using Expand Around Center.
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome (centered at i)
            int len1 = expandAroundCenter(s, i, i);
            // Even length palindrome (centered between i and i + 1)
            int len2 = expandAroundCenter(s, i, i + 1);

            int maxLen = Math.max(len1, len2);
            if (maxLen > end - start) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of palindrome is (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Longest Palindromic Substring ===");

        String s1 = "babad";
        String res1 = longestPalindrome(s1);
        System.out.println("Input: \"" + s1 + "\" -> Output: \"" + res1 + "\"");
        assert res1.equals("bab") || res1.equals("aba") : "Test 1 failed";

        String s2 = "cbbd";
        String res2 = longestPalindrome(s2);
        System.out.println("Input: \"" + s2 + "\" -> Output: \"" + res2 + "\"");
        assert res2.equals("bb") : "Test 2 failed";

        String s3 = "racecar";
        String res3 = longestPalindrome(s3);
        System.out.println("Input: \"" + s3 + "\" -> Output: \"" + res3 + "\"");
        assert res3.equals("racecar") : "Test 3 failed";

        System.out.println("All test assertions passed successfully!");
    }
}
