/*
 * LeetCode Problem #84: Largest Rectangle in Histogram
 * URL: https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 7
 * Memory: 73660000
 * Submission Date: 2026-07-30 07:49:07 UTC
 * Submission ID: 2087163560
 */

class Solution {

    public int largestRectangleArea(int[] heights) {

        int n = heights.length;
        int[] stack = new int[n + 1];
        int top = -1;
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {

            int currentHeight = (i == n) ? 0 : heights[i];

            while (top != -1 && currentHeight < heights[stack[top]]) {

                int height = heights[stack[top--]];

                int width;

                if (top == -1)
                    width = i;
                else
                    width = i - stack[top] - 1;

                maxArea = Math.max(maxArea, height * width);
            }

            stack[++top] = i;
        }

        return maxArea;
    }
}