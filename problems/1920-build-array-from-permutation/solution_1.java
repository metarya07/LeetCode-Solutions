/*
 * LeetCode Problem #1920: Build Array from Permutation
 * URL: https://leetcode.com/problems/build-array-from-permutation/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 47240000
 * Submission Date: 2026-07-24 13:19:16 UTC
 * Submission ID: 2079604774
 */

class Solution {
    public int[] buildArray(int[] nums) {

        int[] ans = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }
}