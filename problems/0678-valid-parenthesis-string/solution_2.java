/*
 * LeetCode Problem #678: Valid Parenthesis String
 * URL: https://leetcode.com/problems/valid-parenthesis-string/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 42752000
 * Submission Date: 2026-10-04 03:54:46 UTC
 * Submission ID: 2161691558
 */

class Solution {
    public boolean checkValidString(String s) {
  int n=s.length();
  int min=0;
  int max=0;
  for(int i=0;i<n;i++){
   if(s.charAt(i)=='('){
    min=min+1;
    max=max+1;
   }
   else if(s.charAt(i)==')'){
    min=min-1;
    max=max-1;
   }
   else{
    min--;
    max++;
   }
   if(min<0) min=0;
   if(max<0) return false;
  }

return (min==0);
    }
}