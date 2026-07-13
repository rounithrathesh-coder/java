/**
 * Java Foundational Exercise - Day 37 (2026-07-13)
 * Topic: Sum of Digits to Power of Length
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex037_Armstrong_Number_Checker {
    public static void main(String[] args) {
        int num = 153, temp = num, sum = 0;
        while (temp > 0) {
            int d = temp % 10;
            sum += d * d * d;
            temp /= 10;
        }
        System.out.println(num + " is Armstrong: " + (sum == num));
    }
}
