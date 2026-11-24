/*
 * LeetCode Problem #3411: Maximum Subarray With Equal Products
 * URL: https://leetcode.com/problems/maximum-subarray-with-equal-products/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 43324000
 * Submission Date: 2026-11-24 04:26:08 UTC
 * Submission ID: 2202314849
 */

class Solution {

    public int maxLength(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            long prodVal = nums[i];
            long gcdVal = nums[i];
            long lcmVal = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                prodVal = prodVal * nums[j];
                gcdVal = gcd(gcdVal, nums[j]);
                lcmVal = (lcmVal * nums[j]) / gcd(lcmVal, nums[j]);
                if (prodVal > gcdVal * lcmVal) {
                    break;
                }
                if (prodVal == gcdVal * lcmVal) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}