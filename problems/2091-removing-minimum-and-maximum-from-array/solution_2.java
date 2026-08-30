/*
 * LeetCode Problem #2091: Removing Minimum and Maximum From Array
 * URL: https://leetcode.com/problems/removing-minimum-and-maximum-from-array/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 86924000
 * Submission Date: 2026-08-30 03:06:02 UTC
 * Submission ID: 2124512333
 */

class Solution {
    public int minimumDeletions(int[] nums) {
        int n=nums.length;
        if(n<=2) return n;
        int maxval=Integer.MIN_VALUE;
        int minval=Integer.MAX_VALUE;
        int minInd=-1;
        int maxInd=-1;
        for(int i=0;i<n;i++)
        {
            if(nums[i]<minval)
            {
                minval=nums[i];
                minInd=i;
            }
            if(nums[i]>maxval)
            {
                maxval=nums[i];
                maxInd=i;
            }
        }
        int a=Math.min(minInd,maxInd);
        int b =Math.max(minInd,maxInd);
         int front=b+1;
         int back=n-a;
         int both=(a+1)+(n-b);
         return Math.min(front,Math.min(back,both));

    }
}