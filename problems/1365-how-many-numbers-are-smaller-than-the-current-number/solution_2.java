/*
 * LeetCode Problem #1365: How Many Numbers Are Smaller Than the Current Number
 * URL: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 8
 * Memory: 47016000
 * Submission Date: 2026-07-23 14:06:52 UTC
 * Submission ID: 2078476635
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