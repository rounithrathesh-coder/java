/**
 * Java Foundational Exercise - Day 72 (2026-08-17)
 * Topic: Divide-and-Conquer Tower of Hanoi Recursion
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex072_Recursion_Tower_Of_Hanoi {
    public static void solveHanoi(int n, char from, char to, char aux) {
        if (n == 1) { System.out.println("Move disk 1 from " + from + " to " + to); return; }
        solveHanoi(n - 1, from, aux, to);
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        solveHanoi(n - 1, aux, to, from);
    }
    public static void main(String[] args) {
        solveHanoi(3, 'A', 'C', 'B');
    }
}
