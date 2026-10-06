/*
 * LeetCode Problem #921: Minimum Add to Make Parentheses Valid
 * URL: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 43112000
 * Submission Date: 2026-10-06 01:29:22 UTC
 * Submission ID: 2163683769
 */

class Solution {

    public int minAddToMakeValid(String s) {
        int openBrackets = 0;
        int minAddsRequired = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openBrackets++;
            } else {
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    minAddsRequired++;
                }
            }
        }
        return minAddsRequired + openBrackets;
    }
}