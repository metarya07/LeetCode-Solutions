/*
 * LeetCode Problem #2778: Sum of Squares of Special Elements 
 * URL: https://leetcode.com/problems/sum-of-squares-of-special-elements/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 46340000
 * Submission Date: 2026-10-11 01:00:46 UTC
 * Submission ID: 2168710101
 */

class Solution {
    public int sumOfSquares(int[] nums) {
        int ans = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (nums.length % i == 0)
                ans += nums[i - 1] * nums[i - 1];
        }
        return ans;
    }
}