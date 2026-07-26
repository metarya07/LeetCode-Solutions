/*
 * LeetCode Problem #26: Remove Duplicates from Sorted Array
 * URL: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 46832000
 * Submission Date: 2026-07-26 12:14:56 UTC
 * Submission ID: 2082000331
 */

class Solution {
    public int removeDuplicates(int[] nums) {

        if (nums.length == 0){
            return 0;
        }

        int i = 0;
        for (int j = 0; j < nums.length; j++ ){
            if (nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
        }

        return i +1; 
    }
}