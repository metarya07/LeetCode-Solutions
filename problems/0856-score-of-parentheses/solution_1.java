/*
 * LeetCode Problem #856: Score of Parentheses
 * URL: https://leetcode.com/problems/score-of-parentheses/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 42264000
 * Submission Date: 2026-10-05 00:04:52 UTC
 * Submission ID: 2162583999
 */

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        int score = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.push(score);
                score = 0;
            }
            else {
                score = st.pop() + Math.max(2 * score, 1);
            }
        }
        return score;
    }
}