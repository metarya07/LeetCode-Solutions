/*
 * LeetCode Problem #453: Minimum Moves to Equal Array Elements
 * URL: https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 6
 * Memory: 47456000
 * Submission Date: 2026-11-01 05:34:19 UTC
 * Submission ID: 2186734733
 */

class Solution {
    public int minMoves(int[] nums) {
        int lo = Arrays.stream(nums).min().getAsInt(), moves = 0;
        for (int x : nums) moves += x - lo;
        return moves;
    }
}