/*
 * LeetCode Problem #1464: Maximum Product of Two Elements in an Array
 * URL: https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 44688000
 * Submission Date: 2026-07-27 06:50:24 UTC
 * Submission ID: 2082964201
 */

class Solution {
    public int maxProduct(int[] nums) {

        int largest = 0;
        int secondLargest = 0;

        for (int num : nums) {

            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } 
            else if (num > secondLargest) {
                secondLargest = num;
            }
        }

        return (largest - 1) * (secondLargest - 1);
    }
}