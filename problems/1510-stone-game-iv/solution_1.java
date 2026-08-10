/*
 * LeetCode Problem #1510: Stone Game IV
 * URL: https://leetcode.com/problems/stone-game-iv/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 14
 * Memory: 42544000
 * Submission Date: 2026-08-10 02:14:04 UTC
 * Submission ID: 2100946990
 */

class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}