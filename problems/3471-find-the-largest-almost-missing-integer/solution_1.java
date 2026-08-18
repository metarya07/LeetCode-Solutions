/*
 * LeetCode Problem #3471: Find the Largest Almost Missing Integer
 * URL: https://leetcode.com/problems/find-the-largest-almost-missing-integer/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 43844000
 * Submission Date: 2026-08-18 01:56:26 UTC
 * Submission ID: 2110775747
 */

class Solution {

    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (n == k) {
            int res = nums[0];
            for (int x : nums) {
                res = Math.max(res, x);
            }
            return res;
        }
        int[] count = new int[51];
        for (int x : nums) {
            count[x]++;
        }
        if (k == 1) {
            for (int i = 50; i >= 0; --i) {
                if (count[i] == 1) {
                    return i;
                }
            }
            return -1;
        }
        int res = -1;
        if (count[nums[0]] == 1) {
            res = Math.max(res, nums[0]);
        }
        if (count[nums[n - 1]] == 1) {
            res = Math.max(res, nums[n - 1]);
        }
        return res;
    }
}