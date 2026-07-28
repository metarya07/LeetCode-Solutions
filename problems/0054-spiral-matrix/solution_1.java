/*
 * LeetCode Problem #54: Spiral Matrix
 * URL: https://leetcode.com/problems/spiral-matrix/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 42932000
 * Submission Date: 2026-07-28 08:56:34 UTC
 * Submission ID: 2084490252
 */

import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> ans = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            for (int j = left; j <= right; j++) {
                ans.add(matrix[top][j]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--;
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    ans.add(matrix[bottom][j]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
                left++;
            }
        }

        return ans;
    }
}