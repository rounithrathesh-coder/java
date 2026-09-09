/**
 * Practice Implementation: Classical 0/1 Knapsack Dynamic Programming with Memoization
 * Revision: #99 | Date: 2026-09-09 10:29:53
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class Knapsack01DP {
    public static int solve(int[] w, int[] v, int cap) {
        int n = w.length;
        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= cap; j++) {
                if (w[i - 1] <= j) dp[i][j] = Math.max(v[i - 1] + dp[i - 1][j - w[i - 1]], dp[i - 1][j]);
                else dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][cap];
    }
    public static void main(String[] args) {
        int[] w = {2, 3, 4, 5}; int[] v = {3, 4, 5, 6};
        System.out.println("Max Value: " + solve(w, v, 5));
    }
}
