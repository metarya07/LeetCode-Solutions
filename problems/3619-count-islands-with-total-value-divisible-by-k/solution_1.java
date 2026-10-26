/*
 * LeetCode Problem #3619: Count Islands With Total Value Divisible by K
 * URL: https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 22 ms
 * Memory: 116.4 MB
 * Submission Date: 2026-10-26 01:10:19 UTC
 * Submission ID: 2181810394
 */

class Solution {

    private int m, n;
    private final int[] dirs = { -1, 0, 1, 0, -1 };

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0 && dfs(grid, i, j) % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private long dfs(int[][] grid, int i, int j) {
        long sum = grid[i][j];
        grid[i][j] = 0;
        for (int d = 0; d < 4; ++d) {
            int x = i + dirs[d],
                y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                sum += dfs(grid, x, y);
            }
        }
        return sum;
    }
}