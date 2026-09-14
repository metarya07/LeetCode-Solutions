/*
 * LeetCode Problem #836: Rectangle Overlap
 * URL: https://leetcode.com/problems/rectangle-overlap/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 42.7 MB
 * Submission Date: 2026-09-14 01:38:41 UTC
 * Submission ID: 2141100748
 */

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // check if either rectangle is actually a line
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] ||
            rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            // the line cannot have positive overlap
            return false;
        }

        return !(rec1[2] <= rec2[0] ||   // left
                 rec1[3] <= rec2[1] ||   // bottom
                 rec1[0] >= rec2[2] ||   // right
                 rec1[1] >= rec2[3]);    // top
    }
}