/*
 * LeetCode Problem #1563: Stone Game V
 * URL: https://leetcode.com/problems/stone-game-v/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 16
 * Memory: 47472000
 * Submission Date: 2026-08-17 04:15:45 UTC
 * Submission ID: 2109643400
 */

class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stoneValue[i];
        }

        int[] f = new int[n + 1];
        int[][] sufMax = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            sufMax[i + 1][i + 1] = Integer.MIN_VALUE;
            sufMax[i][i + 1] = -sum[i]; 
            int preMax = 0;
            int k = i + 1;
            for (int j = i + 2; j <= n; j++) {
                while (sum[k] - sum[i] <= sum[j] - sum[k]) {
                    preMax = Math.max(preMax, f[k] + sum[k]);
                    k++;
                }
                int q = sum[k - 1] - sum[i] == sum[j] - sum[k - 1] ? k - 1 : k;
                f[j] = Math.max(preMax - sum[i], sufMax[q][j] + sum[j]);
                sufMax[i][j] = Math.max(sufMax[i + 1][j], f[j] - sum[i]);
            }
        }

        return f[n];
    }
}