/*
 * LeetCode Problem #3483: Unique 3-Digit Even Numbers
 * URL: https://leetcode.com/problems/unique-3-digit-even-numbers/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 43976000
 * Submission Date: 2026-09-11 03:03:35 UTC
 * Submission ID: 2138077544
 */

class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) freq[d]++;

        int count = 0;

        for (int h = 1; h <= 9; h++) {
            if (freq[h] == 0) continue;
            freq[h]--;

            for (int t = 0; t <= 9; t++) {
                if (freq[t] == 0) continue;
                freq[t]--;

                for (int u = 0; u <= 8; u += 2) {
                    if (freq[u] > 0) count++;
                }

                freq[t]++;
            }

            freq[h]++;
        }

        return count;
    }
}