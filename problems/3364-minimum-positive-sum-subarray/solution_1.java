/*
 * LeetCode Problem #3364: Minimum Positive Sum Subarray 
 * URL: https://leetcode.com/problems/minimum-positive-sum-subarray/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 45588000
 * Submission Date: 2026-11-14 16:44:15 UTC
 * Submission ID: 2196059630
 */

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }
        int best = Integer.MAX_VALUE;
        for (int j = l; j <= n; j++) {
            for (int len = l; len <= Math.min(r, j); len++) {
                int sum = prefix[j] - prefix[j - len];
                if (sum > 0 && sum < best) {
                    best = sum;
                }
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}