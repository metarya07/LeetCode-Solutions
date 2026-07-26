/*
 * LeetCode Problem #169: Majority Element
 * URL: https://leetcode.com/problems/majority-element/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 55720000
 * Submission Date: 2026-07-26 13:54:56 UTC
 * Submission ID: 2082110014
 */

class Solution {
    public int majorityElement(int[] nums) {

        int candidate = 0;
        int count = 0;

        for (int num : nums) {

            if (count == 0){
                candidate = num;
            }

            if (candidate == num){
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}