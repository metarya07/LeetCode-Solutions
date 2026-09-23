/*
 * LeetCode Problem #1658: Minimum Operations to Reduce X to Zero
 * URL: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 4
 * Memory: 101892000
 * Submission Date: 2026-09-23 02:27:42 UTC
 * Submission ID: 2150334284
 */

class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;
        int total = 0;
        for (int num : nums) total += num;

        int target = total - x;

        if (target < 0) return -1;
        if (target == 0) return n;

        int left = 0;
        int sum = 0;
        int longest = -1;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            while (left <= right && sum > target)
                sum -= nums[left++];

            if (sum == target)
                longest = Math.max(longest, right - left + 1);
        }

        return longest == -1 ? -1 : n - longest;
    }
}