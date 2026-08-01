/*
 * LeetCode Problem #486: Predict the Winner
 * URL: https://leetcode.com/problems/predict-the-winner/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 42804000
 * Submission Date: 2026-08-01 03:32:50 UTC
 * Submission ID: 2089409358
 */

class Solution {

    Integer[][] dp;

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new Integer[n][n];
        return solve(nums, 0, n - 1) >= 0;
    }

    private int solve(int[] nums, int left, int right) {
        if (left == right)
            return nums[left];

        if (dp[left][right] != null)
            return dp[left][right];

        int pickLeft = nums[left] - solve(nums, left + 1, right);
        int pickRight = nums[right] - solve(nums, left, right - 1);

        return dp[left][right] = Math.max(pickLeft, pickRight);
    }
}