/*
 * LeetCode Problem #3727: Maximum Alternating Sum of Squares
 * URL: https://leetcode.com/problems/maximum-alternating-sum-of-squares/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 8 ms
 * Memory: 129.6 MB
 * Submission Date: 2026-10-13 03:24:01 UTC
 * Submission ID: 2170743323
 */

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for(int i = 0; i < n; i++){
            if(nums[i]<0) nums[i]*=-1;
            maxVal = Math.max(nums[i], maxVal);
        }
        int[] freq = new int[maxVal+1];
        for(int x : nums) freq[x]++;
        int l = 0, r = maxVal;
        long sum = 0;
        while(r>l ){
            while(l < r && freq[l] == 0)l++;
            while(l < r && freq[r] == 0)r--;
        
            int cnt = Math.min(freq[l], freq[r]);
            freq[l]-=cnt;
            freq[r]-=cnt;
            long diff = r*r - l*l;
            sum+=(diff *cnt);
        }
        if((freq[r] & 1) != 0){
            sum += r*r;
        }
        else if((freq[l] & 1) != 0){
            sum += l*l;
        }
        return sum;
    }
}