/*
 * LeetCode Problem #1524: Number of Sub-arrays With Odd Sum
 * URL: https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 19
 * Memory: 81380000
 * Submission Date: 2026-11-15 00:19:10 UTC
 * Submission ID: 2196232952
 */

class Solution {
    public int numOfSubarrays(int[] arr) {
        Arrays.parallelPrefix(arr, Integer::sum);  // arr becomes its own prefix sums, in place
        long odd = Arrays.stream(arr).map(p -> p & 1).sum();  // odd prefix sums
        return (int) (odd * (arr.length + 1 - odd) % 1_000_000_007);  // the empty prefix is even
    }
}