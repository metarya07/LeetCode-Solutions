/*
 * LeetCode Problem #1386: Cinema Seat Allocation
 * URL: https://leetcode.com/problems/cinema-seat-allocation/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 15
 * Memory: 53148000
 * Submission Date: 2026-08-19 03:32:44 UTC
 * Submission ID: 2112144562
 */

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> d = new HashMap<>();
        for (var e : reservedSeats) {
            int i = e[0], j = e[1];
            d.merge(i, 1 << (10 - j), (x, y) -> x | y);
        }
        int[] masks = {0b0111100000, 0b0000011110, 0b0001111000};
        int ans = (n - d.size()) * 2;
        for (int x : d.values()) {
            for (int mask : masks) {
                if ((x & mask) == 0) {
                    x |= mask;
                    ++ans;
                }
            }
        }
        return ans;
    }
}