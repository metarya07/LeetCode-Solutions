/*
 * LeetCode Problem #1267: Count Servers that Communicate
 * URL: https://leetcode.com/problems/count-servers-that-communicate/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 3
 * Memory: 54876000
 * Submission Date: 2026-11-08 11:57:04 UTC
 * Submission ID: 2191902289
 */

class Solution {

    public int countServers(int[][] grid) {
        int numRows = grid.length;
        int numCols = numRows > 0 ? grid[0].length : 0;
        int communicableServersCount = 0;

        // Traverse through the grid
        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < numCols; ++col) {
                if (grid[row][col] == 1) {
                    boolean canCommunicate = false;

                    // Check for communication in the same row
                    for (int otherCol = 0; otherCol < numCols; ++otherCol) {
                        if (otherCol != col && grid[row][otherCol] == 1) {
                            canCommunicate = true;
                            break;
                        }
                    }

                    // If a server was found in the same row, increment
                    // communicableServersCount
                    if (canCommunicate) {
                        communicableServersCount++;
                    } else {
                        // Check for communication in the same column
                        for (int otherRow = 0; otherRow < numRows; ++otherRow) {
                            if (otherRow != row && grid[otherRow][col] == 1) {
                                canCommunicate = true;
                                break;
                            }
                        }

                        // If a server was found in the same column, increment
                        // communicableServersCount
                        if (canCommunicate) {
                            communicableServersCount++;
                        }
                    }
                }
            }
        }

        return communicableServersCount;
    }
}