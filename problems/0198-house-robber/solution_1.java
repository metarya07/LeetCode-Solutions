/*
 * LeetCode Problem #198: House Robber
 * URL: https://leetcode.com/problems/house-robber/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 42736000
 * Submission Date: 2026-08-01 05:02:11 UTC
 * Submission ID: 2089476744
 */

class Solution {

    Integer[] dp;

    public int rob(int[] nums) {

        dp = new Integer[nums.length];

        return solve(nums, 0);
    }

    private int solve(int[] nums, int index) {

        if (index >= nums.length)
            return 0;

        if (dp[index] != null)
            return dp[index];

        int rob = nums[index] + solve(nums, index + 2);

        int skip = solve(nums, index + 1);

        return dp[index] = Math.max(rob, skip);
    }
}