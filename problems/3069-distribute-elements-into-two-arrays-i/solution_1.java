/*
 * LeetCode Problem #3069: Distribute Elements Into Two Arrays I
 * URL: https://leetcode.com/problems/distribute-elements-into-two-arrays-i/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 46972000
 * Submission Date: 2026-08-20 04:44:49 UTC
 * Submission ID: 2113453326
 */

class Solution {

    public int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        for (int i = 2; i < n; i++) {
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        int[] res = new int[n];
        int idx = 0;
        for (int x : arr1) {
            res[idx++] = x;
        }
        for (int x : arr2) {
            res[idx++] = x;
        }
        return res;
    }
}