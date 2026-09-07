/*
 * LeetCode Problem #940: Distinct Subsequences II
 * URL: https://leetcode.com/problems/distinct-subsequences-ii/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 43432000
 * Submission Date: 2026-09-07 01:41:00 UTC
 * Submission ID: 2133375985
 */

class Solution {
    public int distinctSubseqII(String s) {
        int MOD = (int)1e9 + 7;

        long[] count = new long[26];
        long sum = 0;

        for (char c : s.toCharArray()) {
            long total = (1 + sum) % MOD;
            int idx = c - 'a';

            sum = (sum + total - count[idx] + MOD) % MOD;
            count[idx] = total;
        }

        return (int)sum;
    }
}