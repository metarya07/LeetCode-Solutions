/*
 * LeetCode Problem #1190: Reverse Substrings Between Each Pair of Parentheses
 * URL: https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 8
 * Memory: 42864000
 * Submission Date: 2026-09-27 02:09:51 UTC
 * Submission ID: 2154451965
 */

class Solution {
    public String reverseParentheses(String s) {
        StringBuilder answer = new StringBuilder();
        java.util.Deque<Integer> starts = new java.util.ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                starts.push(answer.length());
            } else if (ch == ')') {
                int left = starts.pop();
                int right = answer.length() - 1;

                while (left < right) {
                    char temp = answer.charAt(left);
                    answer.setCharAt(left, answer.charAt(right));
                    answer.setCharAt(right, temp);

                    left++;
                    right--;
                }
            } else {
                answer.append(ch);
            }
        }

        return answer.toString();
    }
}