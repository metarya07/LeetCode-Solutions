/*
 * LeetCode Problem #1614: Maximum Nesting Depth of the Parentheses
 * URL: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 43036000
 * Submission Date: 2026-09-28 02:57:44 UTC
 * Submission ID: 2155485183
 */

class Solution {
    public int maxDepth(String s) {
        int n = s.length();

        int openCounts = 0;
        int res = 0;
        for(int i = 0; i<n; i++){
            if(s.charAt(i) == '('){
                openCounts += 1;
                res = Math.max(res,openCounts);
            }
            else if(s.charAt(i) == ')'){
                openCounts -=1 ;
            }
            else{
                continue;
            }
        }
        return res;        
    }
}