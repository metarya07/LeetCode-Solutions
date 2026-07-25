/*
 * LeetCode Problem #636: Exclusive Time of Functions
 * URL: https://leetcode.com/problems/exclusive-time-of-functions/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 12
 * Memory: 47324000
 * Submission Date: 2026-07-25 17:08:02 UTC
 * Submission ID: 2081023651
 */

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {

        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        int prevTime = 0;

        for (String log : logs) {

            String[] parts = log.split(":");

            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            int time = Integer.parseInt(parts[2]);

            if (type.equals("start")) {

                if (!stack.isEmpty()) {
                    ans[stack.peek()] += time - prevTime;
                }

                stack.push(id);
                prevTime = time;

            } else {

                ans[stack.pop()] += time - prevTime + 1;
                prevTime = time + 1;
            }
        }

        return ans;
    }
}