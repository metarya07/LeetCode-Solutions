/*
 * LeetCode Problem #3963: Create Grid With Exactly One Path
 * URL: https://leetcode.com/problems/create-grid-with-exactly-one-path/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 3 ms
 * Memory: 45.7 MB
 * Submission Date: 2026-10-27 01:03:04 UTC
 * Submission ID: 2182673945
 */

class Solution {

    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        grid[0] = ".".repeat(n);
        String row = "#".repeat(n - 1) + ".";
        for (int i = 1; i < m; i++) {
            grid[i] = row;
        }
        return grid;
    }
}