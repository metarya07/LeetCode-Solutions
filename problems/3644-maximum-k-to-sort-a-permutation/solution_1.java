/*
 * LeetCode Problem #3644: Maximum K to Sort a Permutation
 * URL: https://leetcode.com/problems/maximum-k-to-sort-a-permutation/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 110288000
 * Submission Date: 2026-10-23 00:29:02 UTC
 * Submission ID: 2179245249
 */

class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i){
            if (nums[i] == i) {
                continue;
            }
            res &= nums[i];
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}