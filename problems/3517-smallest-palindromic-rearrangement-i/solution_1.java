/*
 * LeetCode Problem #3517: Smallest Palindromic Rearrangement I
 * URL: https://leetcode.com/problems/smallest-palindromic-rearrangement-i/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 35
 * Memory: 47896000
 * Submission Date: 2026-07-28 09:31:05 UTC
 * Submission ID: 2084537615
 */

class Solution {
    public String smallestPalindrome(String s) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        String middle = "";

        for (int i = 0; i < 26; i++) {

            for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char) (i + 'a'));
            }

            if (freq[i] % 2 == 1) {
                middle = String.valueOf((char) (i + 'a'));
            }
        }

        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString() + middle + right.toString();
    }
}