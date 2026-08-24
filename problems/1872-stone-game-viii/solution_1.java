/*
 * LeetCode Problem #1872: Stone Game VIII
 * URL: https://leetcode.com/problems/stone-game-viii/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 4
 * Memory: 86824000
 * Submission Date: 2026-08-24 04:58:34 UTC
 * Submission ID: 2118015553
 */

class Solution {

    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] pre = new int[n];
        pre[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + stones[i];
        }

        int[] f = new int[n];
        f[n - 1] = pre[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            f[i] = Math.max(f[i + 1], pre[i] - f[i + 1]);
        }
        return f[1];
    }
}