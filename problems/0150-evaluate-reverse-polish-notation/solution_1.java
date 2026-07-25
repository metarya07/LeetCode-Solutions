/*
 * LeetCode Problem #150: Evaluate Reverse Polish Notation
 * URL: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 6
 * Memory: 45144000
 * Submission Date: 2026-07-25 17:06:20 UTC
 * Submission ID: 2081021426
 */

class Solution {
    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            switch (token) {

                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;

                case "-":
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;

                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;

                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a / b);
                    break;

                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}