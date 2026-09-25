/*
 * LeetCode Problem #1096: Brace Expansion II
 * URL: https://leetcode.com/problems/brace-expansion-ii/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 31
 * Memory: 53016000
 * Submission Date: 2026-09-25 03:29:33 UTC
 * Submission ID: 2152561239
 */

class Solution {
    TreeSet<String> ans = new TreeSet<>();

    void dfs(String s) {
        int r = s.indexOf('}');

        // No braces left
        if (r == -1) {
            ans.add(s);
            return;
        }

        // Find matching '{'
        int l = s.lastIndexOf('{', r);

        String left = s.substring(0, l);
        String right = s.substring(r + 1);

        // Content inside { }
        String inside = s.substring(l + 1, r);

        for (String part : inside.split(",")) {
            dfs(left + part + right);
        }
    }

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(ans);
    }
}