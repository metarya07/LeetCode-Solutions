/*
 * LeetCode Problem #242: Valid Anagram
 * URL: https://leetcode.com/problems/valid-anagram/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 14
 * Memory: 46528000
 * Submission Date: 2026-07-30 15:30:40 UTC
 * Submission ID: 2087693373
 */

import java.util.HashMap;
import java.util.Map;

class Solution {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }

            map.put(c, map.get(c) - 1);

            if (map.get(c) == 0) {
                map.remove(c);
            }
        }

        return map.isEmpty();
    }
}