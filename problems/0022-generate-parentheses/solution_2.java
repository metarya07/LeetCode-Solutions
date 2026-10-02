/*
 * LeetCode Problem #22: Generate Parentheses
 * URL: https://leetcode.com/problems/generate-parentheses/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 43844000
 * Submission Date: 2026-10-02 00:59:58 UTC
 * Submission ID: 2159686642
 */

import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] s = new char[2 * n];
        solve(ans, s, 0, 0, 0, n);
        return ans;
    }

    private void solve(List<String> ans, char[] s, int pos,
                       int open, int close, int n) {

        if (pos == s.length) {
            ans.add(new String(s));
            return;
        }

        if (open < n) {
            s[pos] = '(';
            solve(ans, s, pos + 1, open + 1, close, n);
        }

        if (close < open) {
            s[pos] = ')';
            solve(ans, s, pos + 1, open, close + 1, n);
        }
    }
}