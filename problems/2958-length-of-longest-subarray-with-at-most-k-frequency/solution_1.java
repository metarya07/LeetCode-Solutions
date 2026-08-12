/*
 * LeetCode Problem #2958: Length of Longest Subarray With at Most K Frequency
 * URL: https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 69
 * Memory: 101360000
 * Submission Date: 2026-08-12 03:16:00 UTC
 * Submission ID: 2103585077
 */

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int ans = 0, start = -1;
        Map<Integer, Integer> frequency = new HashMap();
        
        for (int end = 0; end < nums.length; end++) {
            frequency.put(nums[end], frequency.getOrDefault(nums[end], 0) + 1);
            while (frequency.get(nums[end]) > k) {
                start++;
                frequency.put(nums[start], frequency.get(nums[start]) - 1);
            }
            ans = Math.max(ans, end - start);
        }
        
        return ans;
    }
}