/**
 * Java Foundational Exercise - Day 24 (2026-06-30)
 * Topic: Month Days Resolution with Leap Check
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex024_Days_In_Month_Calculator {
    public static void main(String[] args) {
        int month = 2, year = 2024;
        int days = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) ? 29 : 28;
            default -> 0;
        };
        System.out.println("Days: " + days);
    }
}
