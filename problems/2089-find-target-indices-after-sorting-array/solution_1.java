/*
 * LeetCode Problem #2089: Find Target Indices After Sorting Array
 * URL: https://leetcode.com/problems/find-target-indices-after-sorting-array/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 44336000
 * Submission Date: 2026-10-19 03:37:33 UTC
 * Submission ID: 2175855268
 */

class Solution {

    public List<Integer> targetIndices(int[] nums, int target) {
        int cnt1 = 0;
        int cnt2 = 0;

        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] < target) {
                cnt1++;
            } else if (nums[i] == target) {
                cnt2++;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = cnt1; i < cnt1 + cnt2; i++) {
            res.add(i);
        }
        return res;
    }
}