/*
 * LeetCode Problem #2904: Shortest and Lexicographically Smallest Beautiful String
 * URL: https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 5
 * Memory: 46516000
 * Submission Date: 2026-08-26 02:47:23 UTC
 * Submission ID: 2120287035
 */

class Solution {

    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        for (int m = k; m <= n; m++) {
            String ans = "";
            for (int i = m; i <= n; i++) {
                String t = s.substring(i - m, i);
                int cnt = 0;
                for (int j = 0; j < t.length(); j++) {
                    cnt += t.charAt(j) - '0';
                }
                if ((ans.isEmpty() || t.compareTo(ans) < 0) && cnt == k) {
                    ans = t;
                }
            }
            if (!ans.isEmpty()) {
                return ans;
            }
        }
        return "";
    }
}