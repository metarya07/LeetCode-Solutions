/*
 * LeetCode Problem #3731: Find Missing Elements
 * URL: https://leetcode.com/problems/find-missing-elements/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 3
 * Memory: 46680000
 * Submission Date: 2026-08-04 03:38:08 UTC
 * Submission ID: 2093374413
 */

class Solution {

    public List<Integer> findMissingElements(int[] nums) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            set.add(num);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }
}