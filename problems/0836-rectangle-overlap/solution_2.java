/*
 * LeetCode Problem #836: Rectangle Overlap
 * URL: https://leetcode.com/problems/rectangle-overlap/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 42912000
 * Submission Date: 2026-09-14 01:39:45 UTC
 * Submission ID: 2141100953
 */

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return rec1[0] < rec2[2] && rec2[0] < rec1[2] && rec1[1] < rec2[3] && rec2[1] < rec1[3]; 
    }
}