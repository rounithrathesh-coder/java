/**
 * Java Foundational Exercise - Day 04 (2026-06-10)
 * Topic: Short-Circuit Boolean Evaluation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex004_Relational_And_Logical_Operators {
    public static void main(String[] args) {
        int x = 15, y = 25;
        boolean andCheck = (x > 10) && (y < 30);
        boolean orCheck = (x < 10) || (y == 25);
        boolean notCheck = !(x == y);
        System.out.println("AND: " + andCheck + ", OR: " + orCheck + ", NOT: " + notCheck);
    }
}
