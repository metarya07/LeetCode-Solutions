/*
 * LeetCode Problem #1111: Maximum Nesting Depth of Two Valid Parentheses Strings
 * URL: https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 2 ms
 * Memory: 45.6 MB
 * Submission Date: 2026-09-30 00:39:49 UTC
 * Submission ID: 2157645692
 */

class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] result = new int[seq.length()];
        int depth = 0;

        for(int i = 0; i<seq.length(); i++){
            if(seq.charAt(i) == '('){
                depth += 1;
                result[i] = depth % 2;
            }
            else {
                result[i] = depth % 2;
                depth -= 1;
            }
        }

        return result;
    }
}