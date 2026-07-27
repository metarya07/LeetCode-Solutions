/*
 * LeetCode Problem #1475: Final Prices With a Special Discount in a Shop
 * URL: https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 4
 * Memory: 45280000
 * Submission Date: 2026-07-27 08:54:22 UTC
 * Submission ID: 2083097104
 */

import java.util.Stack;

class Solution {
    public int[] finalPrices(int[] prices) {

        int n = prices.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                ans[i] = prices[i];
            } else {
                ans[i] = prices[i] - stack.peek();
            }

            stack.push(prices[i]);
        }

        return ans;
    }
}