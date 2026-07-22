/*
 * LeetCode Problem #1365: How Many Numbers Are Smaller Than the Current Number
 * URL: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 8
 * Memory: 46908000
 * Submission Date: 2026-07-22 12:46:55 UTC
 * Submission ID: 2077108981
 */

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        
        Map<Integer, Integer> map = new HashMap<>();
        int[] copy = nums.clone();
        
        Arrays.sort(copy);
        
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(copy[i], i);
        }
        
        for (int i = 0; i < nums.length; i++) {
            copy[i] = map.get(nums[i]);
        }
        
        return copy;
    }
}