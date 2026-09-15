/*
 * LeetCode Problem #2472: Maximum Number of Non-overlapping Palindrome Substrings
 * URL: https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 1 ms
 * Memory: 43 MB
 * Submission Date: 2026-09-15 04:23:17 UTC
 * Submission ID: 2142172278
 */

class Solution {

    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int ans = 0,
            start = 0;

        for (int r = k - 1; r < n; ++r) {
            int l = r - k + 1;
            if (l >= start && check(s, l, r)) {
                ++ans;
                start = r + 1;
                continue;
            }

            l = r - k;
            if (l >= start && check(s, l, r)) {
                ++ans;
                start = r + 1;
            }
        }

        return ans;
    }

    private boolean check(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}