/*
 * LeetCode Problem #3693: Climbing Stairs II
 * URL: https://leetcode.com/problems/climbing-stairs-ii/
 * Solution #3 (Java)
 * Status: Accepted
 * Runtime: 3
 * Memory: 110012000
 * Submission Date: 2026-11-11 20:26:59 UTC
 * Submission ID: 2194165816
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