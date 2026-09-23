/*
 * LeetCode Problem #1658: Minimum Operations to Reduce X to Zero
 * URL: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 * Solution #3 (java)
 * Status: Accepted
 * Runtime: 1460 ms
 * Memory: 81 MB
 * Submission Date: 2026-09-23 02:28:33 UTC
 * Submission ID: 2150334793
 */

class Solution {
    public int minOperations(int[] nums, int x) {
        // find the longest subarray where sum(subnum) = sub(nums) - x
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        int target = sum - x;
        int left = 0, right = 0;
        int windowSum = 0;
        int maxWindow =Integer.MIN_VALUE;
        while (right < nums.length) {
            int r = nums[right];
            right++;

            //更新数据
            windowSum += r;

            // debug
            System.out.println(target);


            while (left < right && windowSum > target) {
                int l = nums[left];
                left++;

                // update
                windowSum -= l;
            }

            if (windowSum == target) {
                maxWindow = Math.max(maxWindow, right - left);
            }
        }

        return maxWindow == Integer.MIN_VALUE ? -1 : nums.length - maxWindow;
    }
}