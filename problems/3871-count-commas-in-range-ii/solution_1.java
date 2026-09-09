/*
 * LeetCode Problem #3871: Count Commas in Range II
 * URL: https://leetcode.com/problems/count-commas-in-range-ii/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42728000
 * Submission Date: 2026-09-09 06:11:27 UTC
 * Submission ID: 2135957182
 */

class Solution {
    public long countCommas(long n) {
        long count = 0;

        for (long p = 1000; p <= n; p *= 1000)
            count += n - p + 1;

        return count;
    }
}