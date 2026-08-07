/*
 * LeetCode Problem #560: Subarray Sum Equals K
 * URL: https://leetcode.com/problems/subarray-sum-equals-k/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1541
 * Memory: 48472000
 * Submission Date: 2026-08-07 04:48:57 UTC
 * Submission ID: 2097470265
 */

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int n = nums.length;

        for(int i = 0; i < n; i++){
            int sum = 0;

            for(int j = i; j < n; j++){
                sum +=nums[j];

                if(sum == k){
                    count++;
                }
            }
        }
        return count;
    }
}