/*
 * LeetCode Problem #20: Valid Parentheses
 * URL: https://leetcode.com/problems/valid-parentheses/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 43120000
 * Submission Date: 2026-07-27 10:29:25 UTC
 * Submission ID: 2083216554
 */

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else {

                if (stack.isEmpty() || stack.pop() != c)
                    return false;
            }
        }

        return stack.isEmpty();
    }
}