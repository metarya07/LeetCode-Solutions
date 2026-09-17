/*
 * LeetCode Problem #1477: Find Two Non-overlapping Sub-arrays Each With Target Sum
 * URL: https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 61 ms
 * Memory: 145.9 MB
 * Submission Date: 2026-09-17 01:17:43 UTC
 * Submission ID: 2144191887
 */

class Solution {

    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> pos = new HashMap<>();
        pos.put(0, -1);
        int n = arr.length;
        int s = 0;
        int ans = n + 1;
        int minL = n;
        for (int i = 0; i < n; i++) {
            s += arr[i];
            if (pos.containsKey(s - target)) {
                int j = pos.get(s - target);
                int len = i - j;
                ans = Math.min(ans, len + (j == -1 ? n : arr[j]));
                minL = Math.min(minL, len);
            }
            arr[i] = minL;
            pos.put(s, i);
        }
        return ans == n + 1 ? -1 : ans;
    }
}