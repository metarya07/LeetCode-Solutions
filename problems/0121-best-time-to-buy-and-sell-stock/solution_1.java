/*
 * LeetCode Problem #121: Best Time to Buy and Sell Stock
 * URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 94688000
 * Submission Date: 2026-07-27 08:37:22 UTC
 * Submission ID: 2083077436
 */

class Solution {
    public int maxProfit(int[] prices) {

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int num : prices) {

            if (num < minPrice) {
                minPrice = num;
            } else {
                int profit = num - minPrice;
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }
}