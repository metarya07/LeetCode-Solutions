/*
 * LeetCode Problem #3016: Minimum Number of Pushes to Type Word II
 * URL: https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 10
 * Memory: 48280000
 * Submission Date: 2026-07-31 05:40:30 UTC
 * Submission ID: 2088333632
 */

import java.util.Arrays;

class Solution {

    public int minimumPushes(String word) {

        int[] freq = new int[26];

        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);

        int pushes = 0;
        int position = 0;

        for (int i = 25; i >= 0; i--) {

            if (freq[i] == 0) {
                break;
            }

            pushes += freq[i] * ((position / 8) + 1);

            position++;
        }

        return pushes;
    }
}