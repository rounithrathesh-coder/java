/**
 * Java Foundational Exercise - Day 81 (2026-08-26)
 * Topic: Robust Exception Handling and Stack Tracing
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex081_Exception_Handling_Try_Catch {
    public static void main(String[] args) {
        try {
            int res = 10 / 0;
            System.out.println(res);
        } catch (ArithmeticException e) {
            System.out.println("Handled division by zero safely");
        }
    }
}
