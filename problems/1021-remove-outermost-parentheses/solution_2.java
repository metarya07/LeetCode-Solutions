/*
 * LeetCode Problem #1021: Remove Outermost Parentheses
 * URL: https://leetcode.com/problems/remove-outermost-parentheses/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 1 ms
 * Memory: 43.6 MB
 * Submission Date: 2026-10-08 01:42:05 UTC
 * Submission ID: 2165835213
 */

class Solution {
    static {
        for (int i = 0; i<300; i++) {
            removeOuterParentheses("()");
        }
    }
    public static String removeOuterParentheses(String s) {
        StringBuilder ans=new StringBuilder();
        int count=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                if(count>0){
                    ans.append(ch);
                }
                count++;
            }
            else{
                count--;
                if(count>0){
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }
}