/*
 * LeetCode Problem #2091: Removing Minimum and Maximum From Array
 * URL: https://leetcode.com/problems/removing-minimum-and-maximum-from-array/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 3
 * Memory: 86584000
 * Submission Date: 2026-08-30 03:05:43 UTC
 * Submission ID: 2124511726
 */

class Solution {

    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minidx = 0,
            maxidx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minidx]) {
                minidx = i;
            }
            if (nums[i] > nums[maxidx]) {
                maxidx = i;
            }
        }

        int l = Math.min(minidx, maxidx); 
        int r = Math.max(minidx, maxidx); 
        return Math.min(Math.min(r + 1, n - l), l + 1 + n - r);
    }
}