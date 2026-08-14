/**
 * Java Foundational Exercise - Day 69 (2026-08-14)
 * Topic: Efficient In-Place String Mutation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex069_StringBuilder_Buffer_Concatenation {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) sb.append("Item").append(i).append(" ");
        System.out.println("Result: " + sb.toString().trim());
    }
}
