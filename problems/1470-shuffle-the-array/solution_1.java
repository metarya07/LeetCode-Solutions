/*
 * LeetCode Problem #1470: Shuffle the Array
 * URL: https://leetcode.com/problems/shuffle-the-array/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 46532000
 * Submission Date: 2026-07-21 13:38:57 UTC
 * Submission ID: 2075870953
 */

class Solution {
    public int[] shuffle(int[] nums, int n) {

        int[] ans = new int[nums.length];

        for (int i = 0; i < n; i++){
            ans[2*i] = nums[i];
            ans[2 * i + 1] = nums[i + n];
        }
        
        return ans;
    }
}