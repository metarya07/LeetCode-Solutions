/*
 * LeetCode Problem #1441: Build an Array With Stack Operations
 * URL: https://leetcode.com/problems/build-an-array-with-stack-operations/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 44164000
 * Submission Date: 2026-07-23 15:21:17 UTC
 * Submission ID: 2078562941
 */

class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int current = 1;
        for (int i = 0; i < target.length; i++){
            while (current < target[i]){
                ans.add("Push");
                ans.add("Pop");
                current++;
            }

            ans.add("Push");
            current++;
            
        }
        return ans;
    }
}