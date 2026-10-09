/*
 * LeetCode Problem #1541: Minimum Insertions to Balance a Parentheses String
 * URL: https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 10 ms
 * Memory: 47.3 MB
 * Submission Date: 2026-10-09 02:50:47 UTC
 * Submission ID: 2166856252
 */

class Solution {

    public int minInsertions(String s) {
        int insertions = 0;
        int leftCount = 0;
        int length = s.length();
        int index = 0;
        while (index < length) {
            char c = s.charAt(index);
            if (c == '(') {
                leftCount++;
                index++;
            } else {
                if (leftCount > 0) {
                    leftCount--;
                } else {
                    insertions++;
                }
                if (index < length - 1 && s.charAt(index + 1) == ')') {
                    index += 2;
                } else {
                    insertions++;
                    index++;
                }
            }
        }
        insertions += leftCount * 2;
        return insertions;
    }
}