/*
 * LeetCode Problem #3550: Smallest Index With Digit Sum Equal to Index
 * URL: https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/
 * Solution #3 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 45656000
 * Submission Date: 2026-09-24 02:17:20 UTC
 * Submission ID: 2151469742
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