/*
 * LeetCode Problem #3550: Smallest Index With Digit Sum Equal to Index
 * URL: https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 45464000
 * Submission Date: 2026-09-24 02:17:14 UTC
 * Submission ID: 2151469358
 */

class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i], sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            if (sum == i) return i;
        }

        return -1;
    }
}