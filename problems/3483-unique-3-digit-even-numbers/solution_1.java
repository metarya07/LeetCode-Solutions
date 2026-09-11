/*
 * LeetCode Problem #3483: Unique 3-Digit Even Numbers
 * URL: https://leetcode.com/problems/unique-3-digit-even-numbers/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 44976000
 * Submission Date: 2026-09-11 02:55:46 UTC
 * Submission ID: 2138073783
 */

class Solution {

    public int totalNumbers(int[] digits) {
        int n = digits.length;
        boolean[] vis = new boolean[1000];
        int ans = 0;

        for (int i = 0; i < n; ++i) {
            if (digits[i] == 0) {
                continue;
            }
            for (int j = 0; j < n; ++j) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < n; ++k) {
                    if (k == i || k == j || digits[k] % 2 != 0) {
                        continue;
                    }
                    int x = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (!vis[x]) {
                        vis[x] = true;
                        ++ans;
                    }
                }
            }
        }

        return ans;
    }
}