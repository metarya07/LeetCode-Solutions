/*
 * LeetCode Problem #3727: Maximum Alternating Sum of Squares
 * URL: https://leetcode.com/problems/maximum-alternating-sum-of-squares/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 33
 * Memory: 126060000
 * Submission Date: 2026-10-13 03:23:46 UTC
 * Submission ID: 2170743129
 */

class Solution {

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);

        long ans = 0;
        for (int i = 0; i < n / 2; i++) {
            ans -= nums[i];
        }
        for (int i = n / 2; i < n; i++) {
            ans += nums[i];
        }
        return ans;
    }
}