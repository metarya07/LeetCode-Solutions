/*
 * LeetCode Problem #1621: Number of Sets of K Non-Overlapping Line Segments
 * URL: https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 27
 * Memory: 41884000
 * Submission Date: 2026-09-16 05:39:14 UTC
 * Submission ID: 2143312268
 */

class Solution {

    private static final int MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        int[] dp = new int[n];
        int[] prefixSums = new int[n + 1];
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
            prefixSums[j + 1] = (prefixSums[j] + dp[j]) % MOD;
        }
        for (int i = 1; i <= k; i++) {
            dp[0] = 0;
            for (int j = 1; j < n; j++) {
                dp[j] = (dp[j - 1] + prefixSums[j]) % MOD;
            }
            for (int j = 0; j < n; j++) {
                prefixSums[j + 1] = (prefixSums[j] + dp[j]) % MOD;
            }
        }
        return dp[n - 1];
    }
}