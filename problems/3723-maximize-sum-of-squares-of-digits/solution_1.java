/*
 * LeetCode Problem #3723: Maximize Sum of Squares of Digits
 * URL: https://leetcode.com/problems/maximize-sum-of-squares-of-digits/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 53
 * Memory: 47352000
 * Submission Date: 2026-10-14 01:13:39 UTC
 * Submission ID: 2171674683
 */

class Solution {

    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }
        int r = sum % 9;
        StringBuilder ans = new StringBuilder();
        int nines = sum / 9;
        for (int i = 0; i < nines; i++) {
            ans.append('9');
        }
        if (r > 0) {
            ans.append((char) ('0' + r));
        }
        int zeros = num - ans.length();
        for (int i = 0; i < zeros; i++) {
            ans.append('0');
        }
        return ans.toString();
    }
}