/*
 * LeetCode Problem #3105: Longest Strictly Increasing or Strictly Decreasing Subarray
 * URL: https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 43964000
 * Submission Date: 2026-11-12 01:51:43 UTC
 * Submission ID: 2194256193
 */

class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int curI = 1;
        int curD = 1;
        int ans = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i]> nums[i-1]){
                curI++;
                curD = 1;
            }
            else if(nums[i]< nums[i-1]){
                curD++;
                curI = 1;
            }
            else{
                curI=1;
                curD=1;
            }
            ans = Math.max(ans,Math.max(curI,curD));
        }
        return ans;
    }
}