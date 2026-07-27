/*
 * LeetCode Problem #1475: Final Prices With a Special Discount in a Shop
 * URL: https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 45464000
 * Submission Date: 2026-07-27 08:53:25 UTC
 * Submission ID: 2083095982
 */

class Solution {
    public int[] finalPrices(int[] prices) {

        int[] ans = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {

            ans[i] = prices[i];

            for (int j = i + 1; j < prices.length; j++) {

                if (prices[j] <= prices[i]) {
                    ans[i] = prices[i] - prices[j];
                    break;
                }
            }
        }

        return ans;
    }
}