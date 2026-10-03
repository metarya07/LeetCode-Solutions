/*
 * LeetCode Problem #32: Longest Valid Parentheses
 * URL: https://leetcode.com/problems/longest-valid-parentheses/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 44636000
 * Submission Date: 2026-10-03 01:52:04 UTC
 * Submission ID: 2160635342
 */

class Solution {
    public int longestValidParentheses(String s) {
        int answer = 0;
        int open = 0, close = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') open++;
            else close++;

            if (open == close) {
                answer = Math.max(answer, 2 * close);
            } else if (close > open) {
                open = close = 0;
            }
        }

        open = close = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') open++;
            else close++;

            if (open == close) {
                answer = Math.max(answer, 2 * open);
            } else if (open > close) {
                open = close = 0;
            }
        }

        return answer;
    }
}