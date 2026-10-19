/*
 * LeetCode Problem #2089: Find Target Indices After Sorting Array
 * URL: https://leetcode.com/problems/find-target-indices-after-sorting-array/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 44084000
 * Submission Date: 2026-10-19 03:37:42 UTC
 * Submission ID: 2175855491
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessThanTargetCount = 0;
        int targetCount = 0;
        for (int num : nums) {
            if (num < target) {
                lessThanTargetCount++;
            } else if (num == target) {
                targetCount++;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < targetCount; i++) {
            result.add(lessThanTargetCount + i);
        }
        
        return result;
    }
}