/*
 * LeetCode Problem #2124: Check if All A's Appears Before All B's
 * URL: https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42568000
 * Submission Date: 2026-11-22 13:16:09 UTC
 * Submission ID: 2201279231
 */

class Solution {

    public boolean checkString(String s) {
        int n = s.length();
        int lasta = -1; // Index of the last occurrence of 'a'
        int firstb = n; // Index of the first occurrence of 'b'
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                lasta = Math.max(lasta, i);
            } else {
                firstb = Math.min(firstb, i);
            }
        }
        return lasta < firstb;
    }
}