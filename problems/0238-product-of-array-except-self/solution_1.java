/*
 * LeetCode Problem #238: Product of Array Except Self
 * URL: https://leetcode.com/problems/product-of-array-except-self/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 72032000
 * Submission Date: 2026-07-28 14:20:38 UTC
 * Submission ID: 2084871423
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }
        return ans;
    }
}