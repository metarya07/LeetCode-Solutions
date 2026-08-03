/*
 * LeetCode Problem #1406: Stone Game III
 * URL: https://leetcode.com/problems/stone-game-iii/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 10
 * Memory: 85448000
 * Submission Date: 2026-08-03 02:25:01 UTC
 * Submission ID: 2091988556
 */

class Solution {

    public String stoneGameIII(int[] stoneValue) {

        int n = stoneValue.length;

        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {

            dp[i] = Integer.MIN_VALUE;

            int sum = 0;

            for (int j = i; j < n && j < i + 3; j++) {

                sum += stoneValue[j];

                dp[i] = Math.max(dp[i], sum - dp[j + 1]);
            }
        }

        if (dp[0] > 0)
            return "Alice";

        if (dp[0] < 0)
            return "Bob";

        return "Tie";
    }
}