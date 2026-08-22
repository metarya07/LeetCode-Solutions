/*
 * LeetCode Problem #3622: Check Divisibility by Digit Sum and Product
 * URL: https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 42560000
 * Submission Date: 2026-08-22 02:30:30 UTC
 * Submission ID: 2115616182
 */

class Solution {

    public boolean checkDivisibility(int n) {
        int digitSum = 0;
        int digitProduct = 1;
        int original = n;

        while (n > 0) {
            int digit = n % 10;
            n /= 10;

            digitSum += digit;
            digitProduct *= digit;
        }

        return original % (digitSum + digitProduct) == 0;
    }
}