/**
 * Java Foundational Exercise - Day 67 (2026-08-12)
 * Topic: Character Frequency Array Anagram Matching
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex067_String_Anagram_Checker {
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] freq = new int[26];
        for (char c : s1.toCharArray()) freq[c - 'a']++;
        for (char c : s2.toCharArray()) freq[c - 'a']--;
        for (int count : freq) if (count != 0) return false;
        return true;
    }
    public static void main(String[] args) {
        System.out.println("silent & listen: " + isAnagram("silent", "listen"));
    }
}
