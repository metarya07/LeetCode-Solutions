/*
 * LeetCode Problem #88: Merge Sorted Array
 * URL: https://leetcode.com/problems/merge-sorted-array/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 43616000
 * Submission Date: 2026-07-26 12:58:20 UTC
 * Submission ID: 2082044954
 */

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n -1 ;
        int k = m + n -1;

        while (i >= 0 && j >= 0){

            if (nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] =  nums2[j];
                j--;
            }

            k--;
        }

        while (j>= 0){
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}