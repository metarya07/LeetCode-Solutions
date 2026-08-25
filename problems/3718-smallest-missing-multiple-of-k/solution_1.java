/*
 * LeetCode Problem #3718: Smallest Missing Multiple of K
 * URL: https://leetcode.com/problems/smallest-missing-multiple-of-k/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 45336000
 * Submission Date: 2026-08-25 04:03:36 UTC
 * Submission ID: 2119137980
 */

class Solution {

    public int missingMultiple(int[] nums, int k) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            seen.add(num);
        }
        int ans = k;
        while (seen.contains(ans)) {
            ans += k;
        }
        return ans;
    }
}