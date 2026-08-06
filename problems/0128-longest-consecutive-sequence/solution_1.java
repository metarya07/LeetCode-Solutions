/*
 * LeetCode Problem #128: Longest Consecutive Sequence
 * URL: https://leetcode.com/problems/longest-consecutive-sequence/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 30
 * Memory: 95596000
 * Submission Date: 2026-08-06 09:37:16 UTC
 * Submission ID: 2096467358
 */

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) 
            return 0;
        Set<Integer> h = new HashSet<>();
        for (int num : nums) 
            h.add(num);
        int lStreak = 0;
        for (int num : h)
            if (!h.contains(num - 1)) {
                int numm = num;
                int cStreak = 1;
                while (h.contains(numm + 1)) {
                    numm++;
                    cStreak++;
                }
                lStreak = Math.max(lStreak, cStreak);
            }
        return lStreak;
    }
}