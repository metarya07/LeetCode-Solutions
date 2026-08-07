/*
 * LeetCode Problem #560: Subarray Sum Equals K
 * URL: https://leetcode.com/problems/subarray-sum-equals-k/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 23
 * Memory: 48932000
 * Submission Date: 2026-08-07 04:47:59 UTC
 * Submission ID: 2097469215
 */

import java.util.HashMap;

class Solution {

    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {

            prefixSum += num;

            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}