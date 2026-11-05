/*
 * LeetCode Problem #1385: Find the Distance Value Between Two Arrays
 * URL: https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
 * Solution #1 (java)
 * Status: Accepted
 * Runtime: 15 ms
 * Memory: 46 MB
 * Submission Date: 2026-11-05 02:45:12 UTC
 * Submission ID: 2189748207
 */

class Solution {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int cnt = 0;
        for (int x : arr1) {
            boolean ok = true;
            for (int y : arr2) {
                ok &= Math.abs(x - y) > d;
            }
            cnt += ok ? 1 : 0;
        }
        return cnt;
    }
}