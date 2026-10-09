/*
 * LeetCode Problem #1541: Minimum Insertions to Balance a Parentheses String
 * URL: https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 7
 * Memory: 47220000
 * Submission Date: 2026-10-09 02:51:11 UTC
 * Submission ID: 2166856409
 */

class Solution {
    public int minInsertions(String s) {
        int openNeeded = 0;
        int openCount = 0;
        int i = 0;
        int n = s.length();
        char[] arr = s.toCharArray();
        
        while (i < n) {
            if (arr[i] == '(') {
                openCount++;
                i++;
            } else {
                if (i + 1 < n && arr[i + 1] == ')') {
                    i += 2; 
                } else {
                    openNeeded++; 
                    i += 1;
                }
                
                if (openCount > 0) {
                    openCount--;
                } else {
                    openNeeded++;
                }
            }
        }

        openNeeded += openCount * 2;
        
        return openNeeded;
    }
}