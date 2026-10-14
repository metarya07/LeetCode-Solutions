/*
 * LeetCode Problem #3723: Maximize Sum of Squares of Digits
 * URL: https://leetcode.com/problems/maximize-sum-of-squares-of-digits/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 9 ms
 * Memory: 46.8 MB
 * Submission Date: 2026-10-14 01:13:55 UTC
 * Submission ID: 2171674880
 */

class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum) {
            return "";
        }
        int k = sum / 9;
        sum %= 9;
        StringBuilder ans = new StringBuilder("9".repeat(k));
        if (sum > 0) {
            ans.append(sum);
        }
        ans.append("0".repeat(num - ans.length()));
        return ans.toString();
    }
}