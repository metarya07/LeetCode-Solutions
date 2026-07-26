/*
 * LeetCode Problem #283: Move Zeroes
 * URL: https://leetcode.com/problems/move-zeroes/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 47712000
 * Submission Date: 2026-07-26 13:19:29 UTC
 * Submission ID: 2082068210
 */

class Solution {
    public void moveZeroes(int[] nums) {

        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
        }
        while (i < nums.length) {
            nums[i] = 0;
            i++;
        }
    }
}