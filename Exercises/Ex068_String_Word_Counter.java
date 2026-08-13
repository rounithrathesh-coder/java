/**
 * Java Foundational Exercise - Day 68 (2026-08-13)
 * Topic: String Tokenization and Word Boundary Processing
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex068_String_Word_Counter {
    public static void main(String[] args) {
        String text = "Java Object Oriented Programming Fundamentals";
        String[] words = text.trim().split("\\s+");
        System.out.println("Word count: " + words.length);
    }
}
