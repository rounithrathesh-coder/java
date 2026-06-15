/**
 * Java Foundational Exercise - Day 09 (2026-06-15)
 * Topic: Celsius to Fahrenheit Conversion
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex009_Temperature_Converter {
    public static void main(String[] args) {
        double celsius = 37.0;
        double fahrenheit = (celsius * 9.0 / 5.0) + 32.0;
        System.out.printf("%.1f C = %.1f F%n", celsius, fahrenheit);
    }
}
