/*
 * LeetCode Problem #231: Power of Two
 * URL: https://leetcode.com/problems/power-of-two/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42576000
 * Submission Date: 2026-07-31 11:54:21 UTC
 * Submission ID: 2088756364
 */

class Solution {

    public boolean isPowerOfTwo(int n) {

        return n > 0 && (n & (n - 1)) == 0;

    }
}