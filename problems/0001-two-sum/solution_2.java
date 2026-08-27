/*
 * LeetCode Problem #1: Two Sum
 * URL: https://leetcode.com/problems/two-sum/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 46736000
 * Submission Date: 2026-08-27 09:18:12 UTC
 * Submission ID: 2121725969
 */

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n=nums.length;
        Map<Integer,Integer> map=new HashMap<>();
        int[] result=new int[2];
        for(int i=0;i<n;i++){
            if(map.containsKey(target-nums[i])){
                result[1]=i;
                result[0]=map.get(target-nums[i]);
                return result;
            }
            map.put(nums[i],i);
        }
        return result;
    }
}