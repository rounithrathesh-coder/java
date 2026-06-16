/**
 * Java Foundational Exercise - Day 10 (2026-06-16)
 * Topic: Financial Formulas and Math Exponentiation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex010_Simple_And_Compound_Interest {
    public static void main(String[] args) {
        double principal = 10000.0, rate = 7.5, timeYears = 3.0;
        double simpleInterest = (principal * rate * timeYears) / 100.0;
        double compoundInterest = principal * Math.pow((1 + rate / 100.0), timeYears) - principal;
        System.out.printf("SI: $%.2f, CI: $%.2f%n", simpleInterest, compoundInterest);
    }
}
