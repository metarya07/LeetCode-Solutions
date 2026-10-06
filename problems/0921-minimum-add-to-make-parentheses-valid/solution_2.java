/*
 * LeetCode Problem #921: Minimum Add to Make Parentheses Valid
 * URL: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 42656000
 * Submission Date: 2026-10-06 01:29:34 UTC
 * Submission ID: 2163683964
 */

class Solution {
    public int minAddToMakeValid(String s) {
        int count =0;
        Stack<Character> st=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='('){
                st.push(c);
            }else{ 
                if(c==')'&&!st.isEmpty()&&st.peek()=='('){
                    st.pop();
                }else{
                    count++;
                }
            
                
            }
        }
        return Math.abs(count)+st.size();

        
    }
}