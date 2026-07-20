/*
 * LeetCode Problem #1: Two Sum
 * URL: https://leetcode.com/problems/two-sum/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 46
 * Memory: 47068000
 * Submission Date: 2026-07-20 12:59:06 UTC
 * Submission ID: 2074574305
 */

class Solution {
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0;i < nums.length; i++ ){
            for (int j = i + 1 ; j < nums.length; j++){
                if (nums[i]+nums[j] == target){
                    return new int[]{i ,j};
                }
            }
        }

        return new int[]{};
        
    }
}