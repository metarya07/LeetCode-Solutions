/*
 * LeetCode Problem #3870: Count Commas in Range
 * URL: https://leetcode.com/problems/count-commas-in-range/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42248000
 * Submission Date: 2026-09-08 03:20:45 UTC
 * Submission ID: 2134556987
 */

class Solution {

    public int countCommas(int n) {
        return Math.max(n - 999, 0);
    }
}