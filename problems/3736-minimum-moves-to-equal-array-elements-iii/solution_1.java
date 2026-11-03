/*
 * LeetCode Problem #3736: Minimum Moves to Equal Array Elements III
 * URL: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 2 ms
 * Memory: 45.9 MB
 * Submission Date: 2026-11-03 02:57:24 UTC
 * Submission ID: 2188212151
 */

class Solution {

    public int minMoves(int[] nums) {
        int mx = nums[0];
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int ans = 0;
        for (int x : nums) {
            ans += mx - x;
        }
        return ans;
    }
}