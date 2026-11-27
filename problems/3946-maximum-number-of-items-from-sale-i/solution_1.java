/*
 * LeetCode Problem #3946: Maximum Number of Items From Sale I
 * URL: https://leetcode.com/problems/maximum-number-of-items-from-sale-i/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 48
 * Memory: 46816000
 * Submission Date: 2026-11-27 03:45:38 UTC
 * Submission ID: 2204285644
 */

class Solution {

    public int maximumSaleItems(int[][] items, int budget) {
        int[] f = new int[budget + 1];
        int mn = Integer.MAX_VALUE;
        for (int[] item : items) {
            int factor = item[0],
                price = item[1];
            mn = Math.min(mn, price);
            int cnt = 0;
            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    cnt++;
                }
            }
            for (int j = budget; j >= price; j--) {
                f[j] = Math.max(f[j], f[j - price] + cnt);
            }
        }
        int ans = 0;
        for (int j = 0; j <= budget; j++) {
            ans = Math.max(ans, f[j] + (budget - j) / mn);
        }
        return ans;
    }
}