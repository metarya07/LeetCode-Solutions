/*
 * LeetCode Problem #115: Distinct Subsequences
 * URL: https://leetcode.com/problems/distinct-subsequences/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 14
 * Memory: 42880000
 * Submission Date: 2026-09-06 04:10:24 UTC
 * Submission ID: 2132415908
 */

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        if (n > m) return 0;

        long[] dp = new long[n + 1];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[n];
    }
}