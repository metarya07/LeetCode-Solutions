/*
 * LeetCode Problem #3903: Smallest Stable Index I
 * URL: https://leetcode.com/problems/smallest-stable-index-i/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 46164000
 * Submission Date: 2026-09-04 03:22:58 UTC
 * Submission ID: 2130225936
 */

class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffix = new int[n];
 
        int mn = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            mn = Math.min(mn, nums[i]);
            suffix[i] = mn;
        }
 
        int mx = 0;
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            int score = mx - suffix[i];
            if (score <= k)
                return i;
        }
 
        return -1;
    }
}