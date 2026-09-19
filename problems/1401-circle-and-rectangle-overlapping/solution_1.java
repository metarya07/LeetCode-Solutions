/*
 * LeetCode Problem #1401: Circle and Rectangle Overlapping
 * URL: https://leetcode.com/problems/circle-and-rectangle-overlapping/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 41900000
 * Submission Date: 2026-09-19 04:37:59 UTC
 * Submission ID: 2146280250
 */

class Solution {
    public boolean checkOverlap(
        int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int closestXDistance = calculateDistanceToRange(x1, x2, xCenter);
        int closestYDistance = calculateDistanceToRange(y1, y2, yCenter);
        return closestXDistance * closestXDistance
             + closestYDistance * closestYDistance
             <= radius * radius;
    }
    private int calculateDistanceToRange(int rangeStart, int rangeEnd, int point) {
        if (rangeStart <= point && point <= rangeEnd) {
            return 0;
        }
        return point < rangeStart
            ? rangeStart - point
            : point - rangeEnd;
    }
}