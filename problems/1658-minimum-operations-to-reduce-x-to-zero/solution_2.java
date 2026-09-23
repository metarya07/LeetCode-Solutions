/*
 * LeetCode Problem #1658: Minimum Operations to Reduce X to Zero
 * URL: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 4 ms
 * Memory: 101.8 MB
 * Submission Date: 2026-09-23 02:28:02 UTC
 * Submission ID: 2150334538
 */

class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;

        // Calculate total sum
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        // If target is negative, impossible
        if (target < 0) {
            return -1;
        }

        // If target is 0, we need to remove all elements
        if (target == 0) {
            return n;
        }

        int left = 0;
        int sum = 0;
        int maxLength = -1;

        // Sliding window
        for (int right = 0; right < n; right++) {
            sum += nums[right];

            // Shrink window if sum becomes too large
            while (sum > target) {
                sum -= nums[left];
                left++;
            }

            // Found a subarray with target sum
            if (sum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        // If no valid subarray exists
        if (maxLength == -1) {
            return -1;
        }

        return n - maxLength;
    }
}