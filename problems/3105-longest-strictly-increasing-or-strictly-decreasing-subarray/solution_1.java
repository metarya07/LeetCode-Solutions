/*
 * LeetCode Problem #3105: Longest Strictly Increasing or Strictly Decreasing Subarray
 * URL: https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 44036000
 * Submission Date: 2026-11-12 01:51:34 UTC
 * Submission ID: 2194256067
 */

class Solution {

    public int longestMonotonicSubarray(int[] nums) {
        int maxLength = 0;

        // Find longest strictly increasing subarray
        for (int start = 0; start < nums.length; start++) {
            int currLength = 1;
            for (int pos = start + 1; pos < nums.length; pos++) {
                // Extend subarray if next element is larger
                if (nums[pos] > nums[pos - 1]) {
                    currLength++;
                } else {
                    // Break if sequence is not increasing anymore
                    break;
                }
            }
            maxLength = Math.max(maxLength, currLength);
        }

        // Find longest strictly decreasing subarray
        for (int start = 0; start < nums.length; start++) {
            int currLength = 1;
            for (int pos = start + 1; pos < nums.length; pos++) {
                // Extend subarray if next element is smaller
                if (nums[pos] < nums[pos - 1]) {
                    currLength++;
                } else {
                    // Break if sequence is not decreasing anymore
                    break;
                }
            }
            maxLength = Math.max(maxLength, currLength);
        }

        return maxLength; // Return the longer of increasing or decreasing sequences
    }
}