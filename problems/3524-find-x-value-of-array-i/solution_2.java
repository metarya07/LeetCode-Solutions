/*
 * LeetCode Problem #3524: Find X Value of Array I
 * URL: https://leetcode.com/problems/find-x-value-of-array-i/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 9 ms
 * Memory: 91.8 MB
 * Submission Date: 2026-09-21 01:34:44 UTC
 * Submission ID: 2148168141
 */

class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] res = new long[k];
        long[] cnt = new long[k];
        for(int x: nums){
            int mod = x%k;

            long[] tmp = new long[k];
            for(int i=0;i<k;i++){
                int newMod = (i*mod)%k;
                tmp[newMod] += cnt[i];
                res[newMod] += cnt[i];
            }
            res[mod]++;
            tmp[mod]++;
            cnt = tmp;
        }
        return res;
    }
}