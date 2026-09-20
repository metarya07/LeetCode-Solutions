/*
 * LeetCode Problem #3498: Reverse Degree of a String
 * URL: https://leetcode.com/problems/reverse-degree-of-a-string/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 43812000
 * Submission Date: 2026-09-20 02:04:04 UTC
 * Submission ID: 2147127116
 */

class Solution {

    public int reverseDegree(String s) {
        int ans = 0;
        for (int i = 1; i <= s.length(); i++) {
            ans += (26 - (s.charAt(i - 1) - 'a')) * i;
        }
        return ans;
    }
}