/**
 * Java Foundational Exercise - Day 06 (2026-06-12)
 * Topic: Ternary Expression and Inline Decision
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex006_Ternary_Conditional_Operator {
    public static void main(String[] args) {
        int score = 85;
        String result = (score >= 90) ? "Excellent" : (score >= 75) ? "Good" : "Needs Improvement";
        System.out.println("Performance evaluation: " + result);
    }
}
