/**
 * Java Foundational Exercise - Day 59 (2026-08-04)
 * Topic: Matrix Transposition by Swapping Row and Column Indices
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex059_Matrix_Transpose {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] t = new int[3][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) t[j][i] = a[i][j];
        }
        System.out.println("Transpose [0][1]: " + t[0][1]);
    }
}
