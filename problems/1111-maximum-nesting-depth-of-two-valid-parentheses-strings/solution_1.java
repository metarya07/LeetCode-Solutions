/*
 * LeetCode Problem #1111: Maximum Nesting Depth of Two Valid Parentheses Strings
 * URL: https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 45488000
 * Submission Date: 2026-09-30 00:39:30 UTC
 * Submission ID: 2157645538
 */

class Solution {

    public int[] maxDepthAfterSplit(String seq) {
        int length = seq.length();
        int[] ans = new int[length];
        for (int i = 0; i < length; ++i) {
            ans[i] = (i & 1) ^ (seq.charAt(i) == '(' ? 1 : 0);
        }
        return ans;
    }
}