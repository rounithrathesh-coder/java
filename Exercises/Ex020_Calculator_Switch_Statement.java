/**
 * Java Foundational Exercise - Day 20 (2026-06-26)
 * Topic: Arithmetic Engine using Switch Expressions
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex020_Calculator_Switch_Statement {
    public static void main(String[] args) {
        double a = 12.0, b = 4.0;
        char op = '*';
        double res = switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> 0.0;
        };
        System.out.println("Result: " + res);
    }
}
