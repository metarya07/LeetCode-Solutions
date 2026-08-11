/*
 * LeetCode Problem #2996: Smallest Missing Integer Greater Than Sequential Prefix Sum
 * URL: https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 43712000
 * Submission Date: 2026-08-11 03:37:17 UTC
 * Submission ID: 2102225401
 */

class Solution {

    public int missingInteger(int[] nums) {
        int n = nums.length;
        Set<Integer> numSet = new HashSet<>(n);
        for (int num : nums) {
            numSet.add(num);
        }
        int prefixLen = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixLen += 1;
            } else {
                break;
            }
        }

        int total = ((nums[prefixLen - 1] + nums[0]) * prefixLen) / 2;
        while (numSet.contains(total)) {
            total += 1;
        }

        return total;
    }
}