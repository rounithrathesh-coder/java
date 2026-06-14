/**
 * Java Foundational Exercise - Day 08 (2026-06-14)
 * Topic: In-Place Value Swapping using XOR
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex008_Swap_Numbers_Without_Temp {
    public static void main(String[] args) {
        int a = 45, b = 88;
        System.out.println("Before swap: a=" + a + ", b=" + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("After XOR swap: a=" + a + ", b=" + b);
    }
}
