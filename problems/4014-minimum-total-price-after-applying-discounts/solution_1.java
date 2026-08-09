/*
 * LeetCode Problem #4014: Minimum Total Price After Applying Discounts
 * URL: https://leetcode.com/problems/minimum-total-price-after-applying-discounts/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 45
 * Memory: 146016000
 * Submission Date: 2026-08-09 04:41:57 UTC
 * Submission ID: 2099889974
 */

import java.util.Arrays;

class Solution {

    public double minPrice(int[] prices, int[] discounts) {

        Arrays.sort(prices);
        Arrays.sort(discounts);

        double total = 0;

        // Calculate original total
        for (int price : prices) {
            total += price;
        }

        // Apply largest discounts to largest prices
        int n = Math.min(prices.length, discounts.length);

        for (int i = 0; i < n; i++) {

            int price = prices[prices.length - 1 - i];
            int discount = discounts[discounts.length - 1 - i];

            total -= price * discount / 100.0;
        }

        return total;
    }
}