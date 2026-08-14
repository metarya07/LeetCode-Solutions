/*
 * LeetCode Problem #3090: Maximum Length Substring With Two Occurrences
 * URL: https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 43448000
 * Submission Date: 2026-08-14 02:45:45 UTC
 * Submission ID: 2106074106
 */

class Solution {

    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int res = 0;
        for (int right = 0; right < s.length(); right++) {
            int ch = s.charAt(right) - 'a';
            count[ch]++;
            while (count[ch] > 2) {
                int ch2 = s.charAt(left) - 'a';
                count[ch2]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}