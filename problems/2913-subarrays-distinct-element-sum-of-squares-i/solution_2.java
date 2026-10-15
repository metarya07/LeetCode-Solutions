/*
 * LeetCode Problem #2913: Subarrays Distinct Element Sum of Squares I
 * URL: https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 11
 * Memory: 46496000
 * Submission Date: 2026-10-15 01:48:09 UTC
 * Submission ID: 2172598216
 */

class Solution {

    public int sumCounts(List<Integer> nums) {
        int n = nums.size(),
            res = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> s = new HashSet<>();
            for (int j = i; j < n; j++) {
                s.add(nums.get(j));
                res += s.size() * s.size();
            }
        }
        return res;
    }
}