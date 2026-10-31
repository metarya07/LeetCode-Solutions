/*
 * LeetCode Problem #3665: Twisted Mirror Path Count
 * URL: https://leetcode.com/problems/twisted-mirror-path-count/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 174
 * Memory: 280836000
 * Submission Date: 2026-10-31 12:40:39 UTC
 * Submission ID: 2185897424
 */

class Solution {

    public int uniquePaths(int[][] grid) {
        final int MOD = 1000000007;
        int m = grid.length;
        int n = grid[0].length;
        int[][][] f = new int[m][n][2];
        f[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    if (grid[i - 1][j] == 0) {
                        f[i][j][0] = (f[i - 1][j][0] + f[i - 1][j][1]) % MOD;
                    } else {
                        f[i][j][0] = f[i - 1][j][1];
                    }
                }

                if (j > 0) {
                    if (grid[i][j - 1] == 0) {
                        f[i][j][1] = (f[i][j - 1][0] + f[i][j - 1][1]) % MOD;
                    } else {
                        f[i][j][1] = f[i][j - 1][0];
                    }
                }
            }
        }

        return (f[m - 1][n - 1][0] + f[m - 1][n - 1][1]) % MOD;
    }
}