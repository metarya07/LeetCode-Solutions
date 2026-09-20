/*
 * LeetCode Problem #3498: Reverse Degree of a String
 * URL: https://leetcode.com/problems/reverse-degree-of-a-string/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 1 ms
 * Memory: 43.8 MB
 * Submission Date: 2026-09-20 02:04:06 UTC
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