/*
 * LeetCode Problem #1477: Find Two Non-overlapping Sub-arrays Each With Target Sum
 * URL: https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 5 ms
 * Memory: 90.3 MB
 * Submission Date: 2026-09-17 01:18:03 UTC
 * Submission ID: 2144191997
 */

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        int l = 0, sum = 0;
        int ans = Integer.MAX_VALUE;
        int best = Integer.MAX_VALUE;
        
        for (int r = 0; r < n; r++) {
            sum += arr[r];
            
            while (sum > target) {
                sum -= arr[l++];
            }
            
            if (sum == target) {
                int currLen = r - l + 1;
                
                if (l > 0 && minLen[l - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currLen + minLen[l - 1]);
                }
                
                best = Math.min(best, currLen);
            }
            
            minLen[r] = best;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}