/*
 * LeetCode Problem #14: Longest Common Prefix
 * URL: https://leetcode.com/problems/longest-common-prefix/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 3
 * Memory: 43192000
 * Submission Date: 2026-07-20 15:30:23 UTC
 * Submission ID: 2074742209
 */

class Solution {
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";

        for (int i = 0; i < strs[0].length(); i++){
            char current = strs[0].charAt(i);
            for (int j = 1 ; j < strs.length; j++){
                if (i >= strs[j].length() || strs[j].charAt(i) != current){
                    return prefix;
                }
            }

            prefix += current;
        }

        return prefix;
        
    }
}