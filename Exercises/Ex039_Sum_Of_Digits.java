/**
 * Java Foundational Exercise - Day 39 (2026-07-15)
 * Topic: Digit Accumulation with Modulo Loop
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex039_Sum_Of_Digits {
    public static void main(String[] args) {
        int n = 9876, sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        System.out.println("Sum of digits: " + sum);
    }
}
