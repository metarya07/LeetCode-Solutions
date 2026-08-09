/*
 * LeetCode Problem #4016: Maximum Area of Two Non-Overlapping Square Submatrices
 * URL: https://leetcode.com/problems/maximum-area-of-two-non-overlapping-square-submatrices/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 25
 * Memory: 184932000
 * Submission Date: 2026-08-09 04:47:53 UTC
 * Submission ID: 2099894680
 */

class Solution {

    public int maxArea(int[][] mat) {

        // Required variable
        int[][] valmerinto = mat;

        int m = mat.length;
        int n = mat[0].length;

        // dp[r][c] = largest square of 1s
        // starting at (r, c)
        int[][] dp = new int[m + 1][n + 1];

        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {

                if (mat[r][c] == 1) {
                    dp[r][c] = 1 + Math.min(
                            dp[r + 1][c],
                            Math.min(
                                    dp[r][c + 1],
                                    dp[r + 1][c + 1]
                            )
                    );
                }
            }
        }

        int low = 1;
        int high = Math.min(m, n);
        int best = 0;

        // Binary search for the largest possible side length
        while (low <= high) {

            int k = low + (high - low) / 2;

            if (canPlaceTwo(dp, m, n, k)) {
                best = k;
                low = k + 1;
            } else {
                high = k - 1;
            }
        }

        return best * best;
    }

    private boolean canPlaceTwo(int[][] dp, int m, int n, int k) {

        /*
         * Check for vertical separation.
         *
         * If we have one square whose top-left row is
         * at most r-k, and another square starts at row r,
         * they cannot overlap vertically.
         */

        boolean previousRowExists = false;

        for (int r = 0; r + k <= m; r++) {

            if (r >= k) {
                for (int c = 0; c + k <= n; c++) {
                    if (dp[r - k][c] >= k) {
                        previousRowExists = true;
                        break;
                    }
                }
            }

            if (previousRowExists) {
                for (int c = 0; c + k <= n; c++) {
                    if (dp[r][c] >= k) {
                        return true;
                    }
                }
            }
        }

        /*
         * Check for horizontal separation.
         *
         * If one square starts at column <= c-k and another
         * starts at column c, they cannot overlap horizontally.
         */

        boolean previousColumnExists = false;

        for (int c = 0; c + k <= n; c++) {

            if (c >= k) {
                for (int r = 0; r + k <= m; r++) {
                    if (dp[r][c - k] >= k) {
                        previousColumnExists = true;
                        break;
                    }
                }
            }

            if (previousColumnExists) {
                for (int r = 0; r + k <= m; r++) {
                    if (dp[r][c] >= k) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}