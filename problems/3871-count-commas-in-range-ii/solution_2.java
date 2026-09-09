/*
 * LeetCode Problem #3871: Count Commas in Range II
 * URL: https://leetcode.com/problems/count-commas-in-range-ii/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 42648000
 * Submission Date: 2026-09-09 06:11:32 UTC
 * Submission ID: 2135957609
 */

class Solution {
    public long countCommas(long n) {
        if(n<=999) return 0;
        if(n>=1000L&&n<=999999L) return n-1000L+1;
        if(n>=1000000L&&n<=999999999L) return (999999L-1000L+1)+(2L*(n-1000000L+1));
        if(n>=1000000000L&&n<=999999999999L) return (999999L-1000L+1)+(2L*(999999999L-1000000L+1))+(3L*(n-1000000000L+1));
        if(n>=1000000000000L&&n<=999999999999999L) return (999999L-1000L+1)+(2L*(999999999L-1000000L+1))+(3L*(999999999999L-1000000000L+1))+(4L*(n-1000000000000L+1));
        return (999999L-1000L+1)+(2L*(999999999L-1000000L+1))+(3L*(999999999999L-1000000000L+1))+(4L*(999999999999999L-1000000000000L+1))+(5L*(n-1000000000000000L+1));
    }
}