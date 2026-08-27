/*
 * LeetCode Problem #3720: Lexicographically Smallest Permutation Greater Than Target
 * URL: https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 45044000
 * Submission Date: 2026-08-27 02:15:17 UTC
 * Submission ID: 2121392192
 */

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] sCount = new int[26];

        for(int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }
        int startIndex = 0;
        while(startIndex < target.length() && sCount[target.charAt(startIndex) - 'a'] > 0) {
            sCount[target.charAt(startIndex) - 'a'] -= 1;
            startIndex++;
        }
        for(int i = startIndex; i >= 0; i--) {
            if(i < startIndex) {
                sCount[target.charAt(i) - 'a']++;
            }
            if(i < s.length()) {
                int targetChar = target.charAt(i) - 'a';
                for(int c = targetChar + 1; c < 26; c++) {
                    if(sCount[c] > 0) {
                        StringBuilder result = new StringBuilder();
                        result.append(target.substring(0, i));
                        result.append((char)(c + 'a'));
                        sCount[c]--;
                        for(int j = 0; j < 26; j++) {
                            while(sCount[j] > 0) {
                                result.append((char)(j + 'a'));
                                sCount[j]--;
                            }
                        }

                        return result.toString();
                    }
                }
            }
        }
        return "";
    }
}