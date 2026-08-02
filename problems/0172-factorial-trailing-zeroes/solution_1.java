/*
 * LeetCode Problem #172: Factorial Trailing Zeroes
 * URL: https://leetcode.com/problems/factorial-trailing-zeroes/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 42168000
 * Submission Date: 2026-08-02 14:57:11 UTC
 * Submission ID: 2091505437
 */

class Solution {

    public int trailingZeroes(int n) {

        int count = 0;

        while (n > 0) {

            n = n / 5;

            count += n;
        }

        return count;
    }
}