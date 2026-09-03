/*
 * LeetCode Problem #3876: Construct Uniform Parity Array II
 * URL: https://leetcode.com/problems/construct-uniform-parity-array-ii/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 121832000
 * Submission Date: 2026-09-03 01:43:39 UTC
 * Submission ID: 2129057348
 */

class Solution {
    public boolean uniformArray(int[] nums1) {
        int min=Integer.MAX_VALUE;
        for(int num:nums1){
            min=Math.min(min,num);
        }

        //minimum is odd->always possible
        if(min%2==1){
            return true;
        }
        //minimum is even -> all elements must be even 
        for(int num:nums1){
            if(num%2==1){
                return false;
            }
        }
        return true;
        //hello trying
    }
}