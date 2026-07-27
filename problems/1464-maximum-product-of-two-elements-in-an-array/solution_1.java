/*
 * LeetCode Problem #1464: Maximum Product of Two Elements in an Array
 * URL: https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 44364000
 * Submission Date: 2026-07-27 06:45:39 UTC
 * Submission ID: 2082957863
 */

class Solution {
    public int maxProduct(int[] nums) {

        int largest = 0;
        int secondLargest = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > largest) {
                secondLargest = largest;
                largest = nums[i];
            } 
            else if (nums[i] > secondLargest) {
                secondLargest = nums[i];
            }
        }

        return (largest - 1) * (secondLargest - 1);
    }
}