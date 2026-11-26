/*
 * LeetCode Problem #3360: Stone Removal Game
 * URL: https://leetcode.com/problems/stone-removal-game/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 41492000
 * Submission Date: 2026-11-26 05:31:32 UTC
 * Submission ID: 2203663013
 */

class Solution {

    public boolean canAliceWin(int n) {
        int take = 10,
            k = 0;
        while (n >= take) {
            n -= take;
            take--;
            k++;
        }
        return k % 2 == 1;
    }
}