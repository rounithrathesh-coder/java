/**
 * Java Foundational Exercise - Day 57 (2026-08-02)
 * Topic: Element-Wise 2D Matrix Operations
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex057_Matrix_Addition_And_Subtraction {
    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int[][] sum = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) sum[i][j] = a[i][j] + b[i][j];
        }
        System.out.println("Matrix Sum [0][0]: " + sum[0][0]);
    }
}
