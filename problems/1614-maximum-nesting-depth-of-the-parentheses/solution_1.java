/*
 * LeetCode Problem #1614: Maximum Nesting Depth of the Parentheses
 * URL: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 42664000
 * Submission Date: 2026-09-28 00:39:44 UTC
 * Submission ID: 2155433953
 */

class Solution {
    public int maxDepth(String s) {
        int count = 0;
        int res = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                count++;
            }
            if(ch == ')') {
                count--;
            }
            res = Math.max(res,count);
        }
        return res;
    }
}