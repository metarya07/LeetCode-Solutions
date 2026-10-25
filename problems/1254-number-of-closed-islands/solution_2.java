/*
 * LeetCode Problem #1254: Number of Closed Islands
 * URL: https://leetcode.com/problems/number-of-closed-islands/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 2
 * Memory: 46056000
 * Submission Date: 2026-10-25 02:34:43 UTC
 * Submission ID: 2180955209
 */

class Solution {
    private boolean dfs(int[][] grid,int r,int c){
        if(r<0 || c<0 || r>= grid.length || 
        c>=grid[0].length ) return false;
        if(grid[r][c]==1) return true;
        grid[r][c]=1;
        
        boolean up = dfs(grid,r-1,c);
        boolean down = dfs(grid, r+1, c);
        boolean left = dfs(grid, r, c-1);
        boolean right = dfs(grid, r, c+1);
        return  up && down && left && right;
    }

        
    public int closedIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int closedIsl = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]==0){
                    if(dfs(grid,i,j)){
                        closedIsl++;
                    }
                }
            }
        }
        return closedIsl;
    }
}