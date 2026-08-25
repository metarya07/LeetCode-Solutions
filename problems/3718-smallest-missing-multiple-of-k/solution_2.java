/*
 * LeetCode Problem #3718: Smallest Missing Multiple of K
 * URL: https://leetcode.com/problems/smallest-missing-multiple-of-k/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 45112000
 * Submission Date: 2026-08-25 04:04:24 UTC
 * Submission ID: 2119138652
 */

class Solution {
    public int missingMultiple(int[] nums, int k) {
        for(int multiple=k;;multiple+=k){
            boolean found=false;
            for(int i=0;i<nums.length;i++){
                if(nums[i]==multiple){
                    found=true;
                    break;
                }
            }
            if(!found){
            return multiple;
            }
        }
        
    }
}