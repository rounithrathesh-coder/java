/**
 * Java Foundational Exercise - Day 19 (2026-06-25)
 * Topic: Character Switch Case Pattern
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex019_Vowel_Or_Consonant {
    public static void main(String[] args) {
        char ch = 'e';
        switch (Character.toLowerCase(ch)) {
            case 'a', 'e', 'i', 'o', 'u' -> System.out.println(ch + " is a vowel");
            default -> System.out.println(ch + " is a consonant");
        }
    }
}
