/*
 * LeetCode Problem #2029: Stone Game IX
 * URL: https://leetcode.com/problems/stone-game-ix/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 7
 * Memory: 115032000
 * Submission Date: 2026-08-16 03:33:30 UTC
 * Submission ID: 2108465025
 */

class Solution {

    public boolean stoneGameIX(int[] stones) {
        int cnt0 = 0,
            cnt1 = 0,
            cnt2 = 0;
        for (int val : stones) {
            int type = val % 3;
            if (type == 0) {
                ++cnt0;
            } else if (type == 1) {
                ++cnt1;
            } else {
                ++cnt2;
            }
        }
        if (cnt0 % 2 == 0) {
            return cnt1 >= 1 && cnt2 >= 1;
        }
        return cnt1 - cnt2 > 2 || cnt2 - cnt1 > 2;
    }
}