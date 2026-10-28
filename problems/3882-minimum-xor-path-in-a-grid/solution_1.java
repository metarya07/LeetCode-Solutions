/*
 * LeetCode Problem #3882: Minimum XOR Path in a Grid
 * URL: https://leetcode.com/problems/minimum-xor-path-in-a-grid/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 152 ms
 * Memory: 46.6 MB
 * Submission Date: 2026-10-28 16:11:38 UTC
 * Submission ID: 2184060195
 */

class Solution {

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] dp = new boolean[n][1024];
        boolean[] next = new boolean[1024];

        dp[0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int value = grid[i][j];
                Arrays.fill(next, false);

                for (int k = 0; k < 1024; k++) {
                    if (dp[j][k] || (j > 0 && dp[j - 1][k])) {
                        next[k ^ value] = true;
                    }
                }

                System.arraycopy(next, 0, dp[j], 0, 1024);
            }
        }

        for (int k = 0; k < 1024; k++) {
            if (dp[n - 1][k]) {
                return k;
            }
        }

        return -1;
    }
}