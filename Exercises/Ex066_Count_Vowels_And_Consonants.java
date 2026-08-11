/**
 * Java Foundational Exercise - Day 66 (2026-08-11)
 * Topic: Case-Insensitive Character Frequency Filter
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex066_Count_Vowels_And_Consonants {
    public static void main(String[] args) {
        String s = "Hello World";
        int vowels = 0, consonants = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                if ("aeiou".indexOf(c) != -1) vowels++;
                else consonants++;
            }
        }
        System.out.println("Vowels: " + vowels + ", Consonants: " + consonants);
    }
}
