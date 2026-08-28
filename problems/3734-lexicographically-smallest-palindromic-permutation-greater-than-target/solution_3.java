/*
 * LeetCode Problem #3734: Lexicographically Smallest Palindromic Permutation Greater Than Target
 * URL: https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/
 * Solution #3 (Java)
 * Status: Accepted
 * Runtime: 5
 * Memory: 46568000
 * Submission Date: 2026-08-28 03:40:35 UTC
 * Submission ID: 2122493896
 */

class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        String midChar = "";
        int odd = 0;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                odd++;
                midChar = String.valueOf((char) ('a' + i));
            }
        }

        if (odd > 1) {
            return "";
        }

        int[] halfCnt = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        int nHalf = n / 2;
        char[] halfStr = new char[nHalf];

        if (backtrack(
                0,
                false,
                target,
                halfCnt,
                halfStr,
                midChar
        )) {
            return buildPalindrome(halfStr, midChar);
        }

        return "";
    }

    private boolean backtrack(
            int k,
            boolean isGreater,
            String target,
            int[] halfCnt,
            char[] halfStr,
            String midChar
    ) {

        if (k == halfStr.length) {
            String result = buildPalindrome(halfStr, midChar);
            return result.compareTo(target) > 0;
        }

        char startChar = isGreater ? 'a' : target.charAt(k);

        for (char c = startChar; c <= 'z'; c++) {

            int idx = c - 'a';

            if (halfCnt[idx] == 0) {
                continue;
            }

            halfStr[k] = c;
            halfCnt[idx]--;

            boolean newIsGreater =
                    isGreater || c > target.charAt(k);

            if (backtrack(
                    k + 1,
                    newIsGreater,
                    target,
                    halfCnt,
                    halfStr,
                    midChar
            )) {
                return true;
            }

            halfCnt[idx]++;
        }

        return false;
    }

    private String buildPalindrome(char[] halfStr, String midChar) {

        StringBuilder firstHalf = new StringBuilder();

        for (char c : halfStr) {
            firstHalf.append(c);
        }

        StringBuilder secondHalf = new StringBuilder(firstHalf)
                .reverse();

        return firstHalf
                .append(midChar)
                .append(secondHalf)
                .toString();
    }
}