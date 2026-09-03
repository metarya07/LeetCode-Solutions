/*
 * LeetCode Problem #3876: Construct Uniform Parity Array II
 * URL: https://leetcode.com/problems/construct-uniform-parity-array-ii/
 * Solution #3 (Java)
 * Status: Accepted
 * Runtime: 8
 * Memory: 121572000
 * Submission Date: 2026-09-03 01:45:35 UTC
 * Submission ID: 2129051578
 */

class Solution {
    public boolean uniformArray(int[] nums) {
        int smallestOdd = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num % 2 == 1)
                smallestOdd = Math.min(smallestOdd, num);
        }
        if (smallestOdd == Integer.MAX_VALUE)
            return true;
        for (int num : nums) {
            if (num % 2 == 0 && num <= smallestOdd)
                return false;
        }
        return true;
    }
}