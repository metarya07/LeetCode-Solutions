/*
 * LeetCode Problem #3550: Smallest Index With Digit Sum Equal to Index
 * URL: https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 1 ms
 * Memory: 45.6 MB
 * Submission Date: 2026-09-24 02:17:22 UTC
 * Submission ID: 2151469592
 */

class Solution {
  public int smallestIndex(int[] nums) {
    for (int i = 0; i < nums.length; ++i)
      if (getDigitSum(nums[i]) == i)
        return i;
    return -1;
  }

  private int getDigitSum(int num) {
    int sum = 0;
    while (num > 0) {
      sum += num % 10;
      num /= 10;
    }
    return sum;
  }
}