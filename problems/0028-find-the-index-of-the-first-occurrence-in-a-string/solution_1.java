/*
 * LeetCode Problem #28: Find the Index of the First Occurrence in a String
 * URL: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 43008000
 * Submission Date: 2026-08-26 16:42:08 UTC
 * Submission ID: 2121042680
 */

class Solution {
    public int strStr(String haystack, String needle) {
        for(int i = 0, j = needle.length(); j<=haystack.length(); i++,j++){
            if(haystack.substring(i,j).equals(needle)){
                return i;
            }
        }
        return -1;
    }
}