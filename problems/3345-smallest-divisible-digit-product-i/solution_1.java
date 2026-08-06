/*
 * LeetCode Problem #3345: Smallest Divisible Digit Product I
 * URL: https://leetcode.com/problems/smallest-divisible-digit-product-i/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42824000
 * Submission Date: 2026-08-06 01:44:56 UTC
 * Submission ID: 2095992000
 */

class Solution {
    public int smallestNumber(int n, int t) {
        for(int i=n; ;i++)
        {
            int num = i;
            int prod = 1;
            while(num > 0)
            {
                int rem = num %10;
                prod = prod * rem;
                num = num /10;
            } 
            if (prod % t == 0)
            {
                return i;
            }
        }
    }
}