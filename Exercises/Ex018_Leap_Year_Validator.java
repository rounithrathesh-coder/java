/**
 * Java Foundational Exercise - Day 18 (2026-06-24)
 * Topic: Gregorian Calendar Leap Year Logic
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex018_Leap_Year_Validator {
    public static void main(String[] args) {
        int year = 2024;
        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println(year + " is leap year: " + isLeap);
    }
}
