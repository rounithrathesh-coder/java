/**
 * Java Foundational Exercise - Day 22 (2026-06-28)
 * Topic: Progressive Tax Calculation Engine
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex022_Tax_Bracket_Calculator {
    public static void main(String[] args) {
        double income = 850000;
        double tax = 0;
        if (income > 500000) tax += (income - 500000) * 0.20 + 250000 * 0.05;
        System.out.printf("Income: $%.2f, Tax: $%.2f%n", income, tax);
    }
}
