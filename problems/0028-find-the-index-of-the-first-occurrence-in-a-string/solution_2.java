/*
 * LeetCode Problem #28: Find the Index of the First Occurrence in a String
 * URL: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42948000
 * Submission Date: 2026-08-26 16:55:57 UTC
 * Submission ID: 2121060005
 */

class Solution {
    public int strStr(String haystack, String needle) {
        int haylength=haystack.length();
        int needlelength=needle.length();
        if(haylength<needlelength)
            return -1;
        for(int i=0;i<=haystack.length()-needle.length();i++){
            int j=0;
            while(j<needle.length() && haystack.charAt(i+j)==needle.charAt(j))
                j++;
            if(j==needle.length()){
                return i;
            }
        }
        return -1;
    }
}