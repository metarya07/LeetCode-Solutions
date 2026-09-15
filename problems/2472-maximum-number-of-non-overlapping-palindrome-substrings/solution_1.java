/*
 * LeetCode Problem #2472: Maximum Number of Non-overlapping Palindrome Substrings
 * URL: https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 117
 * Memory: 62328000
 * Submission Date: 2026-09-15 04:22:50 UTC
 * Submission ID: 2142171858
 */

class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;

                if (s.charAt(l) == s.charAt(r) &&
                    (len <= 2 || pal[l + 1][r - 1])) {
                    pal[l][r] = true;
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i];

            for (int j = 0; j <= i; j++) {
                if (i - j + 1 >= k && pal[j][i]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}