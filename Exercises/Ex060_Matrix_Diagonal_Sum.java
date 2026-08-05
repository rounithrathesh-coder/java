/**
 * Java Foundational Exercise - Day 60 (2026-08-05)
 * Topic: Primary and Secondary Diagonal Summation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex060_Matrix_Diagonal_Sum {
    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int sum = 0, n = mat.length;
        for (int i = 0; i < n; i++) {
            sum += mat[i][i] + mat[i][n - 1 - i];
        }
        if (n % 2 != 0) sum -= mat[n / 2][n / 2];
        System.out.println("Diagonal sum: " + sum);
    }
}
