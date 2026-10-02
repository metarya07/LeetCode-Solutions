/*
 * LeetCode Problem #22: Generate Parentheses
 * URL: https://leetcode.com/problems/generate-parentheses/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 44624000
 * Submission Date: 2026-10-02 00:59:49 UTC
 * Submission ID: 2159686586
 */

class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();

        getParanthesis(0,0,"",n,res);
        return res;
    }
    static void getParanthesis(int open,int close,String s,int n,ArrayList<String> res)
    {
        if(s.length() == 2*n)
        {
            res.add(s);
        }
        if(open < n)
        {
            getParanthesis(open+1,close,s+"(",n,res);
        }
        if(close < open)
        {
            getParanthesis(open,close+1,s+")",n,res);
        }
    }
}