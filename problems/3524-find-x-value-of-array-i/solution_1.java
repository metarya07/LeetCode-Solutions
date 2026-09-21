/*
 * LeetCode Problem #3524: Find X Value of Array I
 * URL: https://leetcode.com/problems/find-x-value-of-array-i/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 13 ms
 * Memory: 92.1 MB
 * Submission Date: 2026-09-21 01:34:26 UTC
 * Submission ID: 2148168050
 */

class Solution {

    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result = new long[k];
        long[] dp = new long[k]; // Initial state: no elements have been processed, so no non-empty subarray exists.

        for (int i = 0; i < n; i++) {
            long[] ndp = new long[k]; // Current-layer state (rolling array).
            ndp[nums[i] % k]++;
            for (int r = 0; r < k; r++) {
                ndp[(int) (((long) r * nums[i]) % k)] += dp[r];
            }
            dp = ndp; // Update the state.
            // Accumulate the answer.
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }

        return result;
    }
}