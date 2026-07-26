/*
 * LeetCode Problem #27: Remove Element
 * URL: https://leetcode.com/problems/remove-element/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 43648000
 * Submission Date: 2026-07-26 12:31:58 UTC
 * Submission ID: 2082017487
 */

class Solution {
    public int removeElement(int[] nums, int val) {

        int i = 0;

        for (int j = 0; j < nums.length; j++) {

            if (nums[j] != val) {

                nums[i] = nums[j];
                i++;

            }
        }

        return i;
    }
}