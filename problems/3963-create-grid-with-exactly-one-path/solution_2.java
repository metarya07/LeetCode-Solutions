/*
 * LeetCode Problem #3963: Create Grid With Exactly One Path
 * URL: https://leetcode.com/problems/create-grid-with-exactly-one-path/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 2 ms
 * Memory: 45.9 MB
 * Submission Date: 2026-10-27 01:03:19 UTC
 * Submission ID: 2182674024
 */

class Solution {
    public String[] createGrid(int m, int n) {
        var res = new String[m];
        var sb = new StringBuilder();
        for (var i = 0; i < n; i++) {
            sb.append(".");
        }
        res[0] = sb.toString();

        sb = new StringBuilder();
        for (var i = 1; i < n; i++) {
            sb.append("#");
        }
        sb.append(".");
        
        for(var i = 1; i < m; i++) {
            res[i] = sb.toString();
        }
        
        return res;
    }
}