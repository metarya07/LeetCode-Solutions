/*
 * LeetCode Problem #3942: Minimum Operations to Sort a Permutation
 * URL: https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 4 ms
 * Memory: 94.1 MB
 * Submission Date: 2026-10-22 02:04:45 UTC
 * Submission ID: 2178428412
 */

class Solution {

    public int minOperations(int[] nums) {
        final int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int start = 0;
        while (nums[start] != 0) {
            start++;
        }

        boolean asc = true;
        boolean desc = true;

        for (int i = 1; i < n; i++) {
            final int value = nums[(start + i) % n];

            if (value != i) {
                asc = false;
            }
            if (value != n - i) {
                desc = false;
            }

            if (!asc && !desc) {
                return -1;
            }
        }

        if (asc) {
            return Math.min(start, n - start + 2);
        }

        return Math.min(n - start, start + 2);
    }
}