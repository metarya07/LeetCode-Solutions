/*
 * LeetCode Problem #3644: Maximum K to Sort a Permutation
 * URL: https://leetcode.com/problems/maximum-k-to-sort-a-permutation/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 109996000
 * Submission Date: 2026-10-23 00:29:11 UTC
 * Submission ID: 2179245361
 */

class Solution {
    public int sortPermutation(int[] nums) {
        int n=nums.length;
        int k=(1<<17)-1;
        for(int i=0;i<n;i++){
            if(nums[i]==i) continue;
            k&=nums[i];
        }
        return k==((1<<17)-1)?0:k;
    }
}