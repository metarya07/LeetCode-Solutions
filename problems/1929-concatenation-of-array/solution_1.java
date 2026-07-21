/*
 * LeetCode Problem #1929: Concatenation of Array
 * URL: https://leetcode.com/problems/concatenation-of-array/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 47044000
 * Submission Date: 2026-07-21 13:23:38 UTC
 * Submission ID: 2075852883
 */

class Solution {
    public int[] getConcatenation(int[] nums) {

        int[] ans = new int[nums.length * 2];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[i + nums.length] = nums[i];
        }

        return ans;
    }
}