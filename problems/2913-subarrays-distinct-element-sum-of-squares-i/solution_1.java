/*
 * LeetCode Problem #2913: Subarrays Distinct Element Sum of Squares I
 * URL: https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 4
 * Memory: 45020000
 * Submission Date: 2026-10-15 01:47:59 UTC
 * Submission ID: 2172605836
 */

class Solution {
    public int sumCounts(List<Integer> nums) {
        int result = 0;
        int n = nums.size();
        
        for(int i =0; i< n; i++) {
            int count = 0;
            boolean[] visited = new boolean[101];
            for(int j=i; j< n; j++) {
                int num = nums.get(j);
                if(!visited[num]) {
                    visited[num] = true;
                    count++;
                }
                result += count*count;
            }
            
        }

        return result;
    }
}