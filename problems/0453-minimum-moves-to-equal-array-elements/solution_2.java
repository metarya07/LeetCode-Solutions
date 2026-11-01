/*
 * LeetCode Problem #453: Minimum Moves to Equal Array Elements
 * URL: https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 2 ms
 * Memory: 46.8 MB
 * Submission Date: 2026-11-01 05:34:37 UTC
 * Submission ID: 2186735052
 */

class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            sum += num;
        }
        return sum - (min * nums.length);
    }
}