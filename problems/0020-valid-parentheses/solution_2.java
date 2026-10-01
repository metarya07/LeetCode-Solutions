/*
 * LeetCode Problem #20: Valid Parentheses
 * URL: https://leetcode.com/problems/valid-parentheses/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 43192000
 * Submission Date: 2026-10-01 00:26:55 UTC
 * Submission ID: 2158658984
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