/*
 * LeetCode Problem #9: Palindrome Number
 * URL: https://leetcode.com/problems/palindrome-number/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 5
 * Memory: 45848000
 * Submission Date: 2026-07-20 14:45:37 UTC
 * Submission ID: 2074690239
 */

class Solution {
    public boolean isPalindrome(int x) {
        if (x<0){
            return false;
        }

        int orignial = x, reverse = 0;

        while ( x > 0){
            int digit = x % 10;
            reverse = reverse * 10 + digit;
            x = x/10;
        } 

        return orignial == reverse;
    }
}