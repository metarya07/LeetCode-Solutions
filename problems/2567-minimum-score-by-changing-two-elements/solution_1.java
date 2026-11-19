/*
 * LeetCode Problem #2567: Minimum Score by Changing Two Elements
 * URL: https://leetcode.com/problems/minimum-score-by-changing-two-elements/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 18
 * Memory: 59344000
 * Submission Date: 2026-11-19 16:11:31 UTC
 * Submission ID: 2199457549
 */

class Solution {

    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.min(
            Math.min(nums[n - 3] - nums[0], nums[n - 2] - nums[1]),
            nums[n - 1] - nums[2]
        );
    }
}