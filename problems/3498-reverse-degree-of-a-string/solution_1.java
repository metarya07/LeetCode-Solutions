/*
 * LeetCode Problem #3498: Reverse Degree of a String
 * URL: https://leetcode.com/problems/reverse-degree-of-a-string/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 1 ms
 * Memory: 44.5 MB
 * Submission Date: 2026-09-20 02:03:50 UTC
 * Submission ID: 2147127045
 */

class Solution {

    public int reverseDegree(String s) {

        int result = 0;

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            int charVal = 26 - (chars[i] - 'a');

            result += charVal * (i + 1);
        }

        return result;
    }
}