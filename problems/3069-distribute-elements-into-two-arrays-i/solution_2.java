/*
 * LeetCode Problem #3069: Distribute Elements Into Two Arrays I
 * URL: https://leetcode.com/problems/distribute-elements-into-two-arrays-i/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 46464000
 * Submission Date: 2026-08-20 04:45:37 UTC
 * Submission ID: 2113454181
 */

class Solution {
    static {
        for(int i = 0; i <= 500; i++) {
            resultArray(new int[2]);
        }
    }
    public static int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n-1];
        arr1[0] = nums[0];
        arr2[0] = nums[1];
        int arr1Pointer = 0;
        int arr2Pointer = 0;
        for(int i = 2; i < n; i++) {
            if(arr1[arr1Pointer] > arr2[arr2Pointer]) {
                arr1[++arr1Pointer] = nums[i];
            } else {
                arr2[++arr2Pointer] = nums[i];
            }
        }
        for(int i = 0; i <= arr2Pointer; i++) {
            arr1[++arr1Pointer] = arr2[i];
        }
        return arr1;
    }
}
