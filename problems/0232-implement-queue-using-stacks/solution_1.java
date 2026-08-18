/*
 * LeetCode Problem #232: Implement Queue using Stacks
 * URL: https://leetcode.com/problems/implement-queue-using-stacks/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42864000
 * Submission Date: 2026-08-18 13:26:47 UTC
 * Submission ID: 2111456485
 */

class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        s1.push(x);

        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    public int pop() {
        return s1.pop();
    }

    public int peek() {
        return s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty();
    }
}