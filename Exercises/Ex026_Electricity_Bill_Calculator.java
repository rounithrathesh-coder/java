/**
 * Java Foundational Exercise - Day 26 (2026-07-02)
 * Topic: Tiered Utility Pricing Calculations
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex026_Electricity_Bill_Calculator {
    public static void main(String[] args) {
        int units = 250;
        double bill = 0;
        if (units <= 100) bill = units * 1.5;
        else if (units <= 200) bill = 100 * 1.5 + (units - 100) * 2.5;
        else bill = 100 * 1.5 + 100 * 2.5 + (units - 200) * 4.0;
        System.out.printf("Total Bill: $%.2f%n", bill);
    }
}
