/*
 * LeetCode Problem #739: Daily Temperatures
 * URL: https://leetcode.com/problems/daily-temperatures/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 60
 * Memory: 107716000
 * Submission Date: 2026-07-29 05:53:50 UTC
 * Submission ID: 2085651752
 */

class Solution {

    public int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;

        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty()
                    && temperatures[i] > temperatures[stack.peek()]) {

                int index = stack.pop();

                ans[index] = i - index;
            }

            stack.push(i);
        }

        return ans;
    }
}