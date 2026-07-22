/*
 * LeetCode Problem #645: Set Mismatch
 * URL: https://leetcode.com/problems/set-mismatch/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 17
 * Memory: 47700000
 * Submission Date: 2026-07-22 12:11:20 UTC
 * Submission ID: 2077075656
 */

import java.util.HashMap;

class Solution {
    public int[] findErrorNums(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequency of each number
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        int duplicate = -1;
        int missing = -1;

        // Find duplicate and missing numbers
        for (int i = 1; i <= nums.length; i++) {
            if (!map.containsKey(i)) {
                missing = i;
            } else if (map.get(i) == 2) {
                duplicate = i;
            }
        }

        return new int[] { duplicate, missing };
    }
}