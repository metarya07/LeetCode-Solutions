/*
 * LeetCode Problem #42: Trapping Rain Water
 * URL: https://leetcode.com/problems/trapping-rain-water/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 47904000
 * Submission Date: 2026-07-28 14:55:26 UTC
 * Submission ID: 2084916997
 */

class Solution {
    public int trap(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int water = 0;

        while (left < right) {

            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                water += leftMax - height[left];
                left++;
            } else {
                water += rightMax - height[right];
                right--;
            }
        }

        return water;
    }
}