/*
 * LeetCode Problem #1807: Evaluate the Bracket Pairs of a String
 * URL: https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 29
 * Memory: 98064000
 * Submission Date: 2026-09-26 03:04:39 UTC
 * Submission ID: 2153462508
 */

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> knowledgeMap = new HashMap<>(knowledge.size());
        for (List<String> pair : knowledge) {
            knowledgeMap.put(pair.get(0), pair.get(1));
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                int closingBracketIndex = s.indexOf(')', i + 1);
                String key = s.substring(i + 1, closingBracketIndex);
                result.append(knowledgeMap.getOrDefault(key, "?"));
                i = closingBracketIndex;
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }
}