/**
 * Java Foundational Exercise - Day 58 (2026-08-03)
 * Topic: Standard Matrix Dot Product Algorithm
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex058_Matrix_Multiplication {
    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{2, 0}, {1, 2}};
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) c[i][j] += a[i][k] * b[k][j];
            }
        }
        System.out.println("Product [0][0]: " + c[0][0]);
    }
}
