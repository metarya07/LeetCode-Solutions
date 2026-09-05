/*
 * LeetCode Problem #3904: Smallest Stable Index II
 * URL: https://leetcode.com/problems/smallest-stable-index-ii/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 4
 * Memory: 132984000
 * Submission Date: 2026-09-05 04:29:34 UTC
 * Submission ID: 2131286420
 */

class Solution {

    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] minValue = new int[n];
        minValue[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minValue[i] = Math.min(minValue[i + 1], nums[i]);
        }

        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, nums[i]);
            if (maxValue - minValue[i] <= k) {
                return i;
            }
        }
        return -1;
    }
}