/**
 * Java Foundational Exercise - Day 21 (2026-06-27)
 * Topic: Score Range Evaluation and Grading
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex021_Grade_Evaluation_System {
    public static void main(String[] args) {
        int marks = 88;
        char grade = (marks >= 90) ? 'O' : (marks >= 80) ? 'A' : (marks >= 70) ? 'B' : 'C';
        System.out.println("Grade awarded: " + grade);
    }
}
