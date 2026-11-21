/*
 * LeetCode Problem #3041: Maximize Consecutive Elements in an Array After Modification
 * URL: https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 100
 * Memory: 118228000
 * Submission Date: 2026-11-21 01:19:38 UTC
 * Submission ID: 2200272585
 */

class Solution {

    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;
        for (int x : nums) {
            dp.put(
                x + 1,
                Math.max(dp.getOrDefault(x + 1, 0), dp.getOrDefault(x, 0) + 1)
            );
            dp.put(
                x,
                Math.max(dp.getOrDefault(x, 0), dp.getOrDefault(x - 1, 0) + 1)
            );
            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }
        return ans;
    }
}