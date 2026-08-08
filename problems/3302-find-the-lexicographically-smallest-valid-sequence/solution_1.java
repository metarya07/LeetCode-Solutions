/*
 * LeetCode Problem #3302: Find the Lexicographically Smallest Valid Sequence
 * URL: https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 36
 * Memory: 137452000
 * Submission Date: 2026-08-08 02:08:15 UTC
 * Submission ID: 2098549968
 */

class Solution {

    public int[] validSequence(String word1, String word2) {
        int n = word1.length(),
            m = word2.length();
        int[] last = new int[m];
        Arrays.fill(last, -1);
        int j = m - 1;
        for (int i = n - 1; i >= 0; --i) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j -= 1;
            }
        }
        int[] res = new int[m];
        int skip = 0;
        j = 0;
        for (int i = 0; i < n; ++i) {
            if (j == m) break;
            if (
                word1.charAt(i) == word2.charAt(j) ||
                (skip == 0 && (j == m - 1 || i < last[j + 1]))
            ) {
                skip += word1.charAt(i) != word2.charAt(j) ? 1 : 0;
                res[j] = i;
                j += 1;
            }
        }
        return j == m ? res : new int[0];
    }
}