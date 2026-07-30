/*
 * LeetCode Problem #3014: Minimum Number of Pushes to Type Word I
 * URL: https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 43228000
 * Submission Date: 2026-07-30 03:07:05 UTC
 * Submission ID: 2086837288
 */

class Solution {

    public int minimumPushes(String word) {

        int pushes = 0;

        for (int i = 0; i < word.length(); i++) {

            pushes += (i / 8) + 1;

        }

        return pushes;
    }
}