/*
 * LeetCode Problem #485: Max Consecutive Ones
 * URL: https://leetcode.com/problems/max-consecutive-ones/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 3
 * Memory: 52608000
 * Submission Date: 2026-07-22 11:53:04 UTC
 * Submission ID: 2077059877
 */

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {

        int current = 0;
        int max = 0;

        for ( int i = 0;i < nums.length; i++ ){
            if (nums[i]==1){
                current++;
                max = Math.max(max, current);
            } else {
                current = 0;
            }
        }

        return max;
    }
}