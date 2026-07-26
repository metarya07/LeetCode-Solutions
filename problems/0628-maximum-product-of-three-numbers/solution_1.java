/*
 * LeetCode Problem #628: Maximum Product of Three Numbers
 * URL: https://leetcode.com/problems/maximum-product-of-three-numbers/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 15
 * Memory: 48116000
 * Submission Date: 2026-07-26 13:32:30 UTC
 * Submission ID: 2082083250
 */

import java.util.*;

class Solution {
    public int maximumProduct(int[] nums) {

        Arrays.sort(nums);
        int n = nums.length;
        int product1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int product2 = nums[0] * nums[1] * nums[n - 1];

        return Math.max(product1, product2);

    }
}