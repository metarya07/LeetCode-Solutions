/*
 * LeetCode Problem #1190: Reverse Substrings Between Each Pair of Parentheses
 * URL: https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42948000
 * Submission Date: 2026-09-27 02:10:50 UTC
 * Submission ID: 2154452204
 */

class Solution {
    public String reverseParentheses(String s) {

        int n = s.length();
        Deque<Integer> stk = new ArrayDeque<>();
        int[] pair = new int[n];

        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                stk.push(i);
            }else if(ch == ')'){
                int open = stk.pop();
                pair[open] = i;
                pair[i] = open;
            }
        }

        StringBuilder sb = new StringBuilder();
        int direction = 1;

        for(int i=0; i<n; i += direction){
            
            char ch = s.charAt(i);

            if(ch == '(' || ch == ')'){
                i = pair[i];
                direction = -direction;
            }else{
                sb.append(ch);
            }

        }

        return sb.toString();
    }
}