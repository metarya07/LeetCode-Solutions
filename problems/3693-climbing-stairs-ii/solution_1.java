/*
 * LeetCode Problem #3693: Climbing Stairs II
 * URL: https://leetcode.com/problems/climbing-stairs-ii/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 3 ms
 * Memory: 110.1 MB
 * Submission Date: 2026-11-11 20:25:50 UTC
 * Submission ID: 2194165323
 */

class Solution {

    public int climbStairs(int n, int[] costs) {
        int a = 0,
            b = 0,
            c = 0;
        for (int i = 0; i < n; ++i) {
            int nxt = Math.min(Math.min(a + 9, b + 4), c + 1) + costs[i];
            a = b;
            b = c;
            c = nxt;
        }
        return c;
    }
}