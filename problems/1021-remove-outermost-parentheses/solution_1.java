/*
 * LeetCode Problem #1021: Remove Outermost Parentheses
 * URL: https://leetcode.com/problems/remove-outermost-parentheses/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 4 ms
 * Memory: 43.4 MB
 * Submission Date: 2026-10-08 01:41:47 UTC
 * Submission ID: 2165835100
 */

class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int lvl = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c == '(' && lvl++ > 0) || (c == ')' && lvl-- > 1))
                sb.append(c);
        }
        
        return sb.toString();
    }
}