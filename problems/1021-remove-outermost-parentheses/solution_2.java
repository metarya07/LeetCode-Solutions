/*
 * LeetCode Problem #1021: Remove Outermost Parentheses
 * URL: https://leetcode.com/problems/remove-outermost-parentheses/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 43636000
 * Submission Date: 2026-10-08 01:41:59 UTC
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