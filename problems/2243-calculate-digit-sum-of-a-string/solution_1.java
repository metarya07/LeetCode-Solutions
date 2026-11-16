/*
 * LeetCode Problem #2243: Calculate Digit Sum of a String
 * URL: https://leetcode.com/problems/calculate-digit-sum-of-a-string/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42864000
 * Submission Date: 2026-11-16 06:03:10 UTC
 * Submission ID: 2197083140
 */

class Solution {

    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder tmp = new StringBuilder(); // The string after each round
            int n = s.length();
            for (int i = 0; i < n; i += k) {
                int val = 0;
                for (int j = i; j < Math.min(i + k, n); ++j) {
                    val += s.charAt(j) - '0';
                }
                tmp.append(val);
            }
            s = tmp.toString();
        }
        return s;
    }
}