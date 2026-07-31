/*
 * LeetCode Problem #49: Group Anagrams
 * URL: https://leetcode.com/problems/group-anagrams/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 6
 * Memory: 49404000
 * Submission Date: 2026-07-31 06:14:56 UTC
 * Submission ID: 2088381576
 */

import java.util.*;

class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            String word = strs[i];

            char[] chars = word.toCharArray();

            Arrays.sort(chars);

            String key = new String(chars);

            map.putIfAbsent(key, new ArrayList<>());

            map.get(key).add(word);
        }

        return new ArrayList<>(map.values());
    }
}