/*
 * LeetCode Problem #3942: Minimum Operations to Sort a Permutation
 * URL: https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 3
 * Memory: 94516000
 * Submission Date: 2026-10-22 12:18:30 UTC
 * Submission ID: 2178787856
 */

class Solution {
    public int minOperations(int[] nums) {
        int n=nums.length;
        if(n==1)return 0;
        int dec=-1;
        for(int i=1;i<n;i++){
            if(nums[i]!=nums[i-1]+1){
                if(dec!=-1){
                    dec=n+1;
                    break;
                }
                dec=i;
            }
        }
        if(dec==-1)return 0;
        if(dec<n)return Math.min(dec,2+(n-dec));
        //check for dec
        int inc=-1;
        for(int i=1;i<n;i++){
            if(nums[i]!=nums[i-1]-1){
                if(inc!=-1){
                    inc=n+1;
                    break;
                }
                inc=i;
            }
        }
        if(inc==-1)return 1;
        if(inc<n){
            return Math.min(inc,n-inc)+1;
        }
        return -1;
    }
}