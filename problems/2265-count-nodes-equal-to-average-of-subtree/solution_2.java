/*
 * LeetCode Problem #2265: Count Nodes Equal to Average of Subtree
 * URL: https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 45.7 MB
 * Submission Date: 2026-09-10 02:11:55 UTC
 * Submission ID: 2136953547
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

        int result=0;
    public int averageOfSubtree(TreeNode root) {
        solve(root);

        return result;
    }

    private int[] solve(TreeNode root){
        if(root==null){
            return new int[]{0,0};
        }
        int sum=0;
        int count =0;
        int[] left = solve(root.left);
        int[] right =solve(root.right);
        sum+=left[0]+right[0]+root.val;
        count+=left[1]+right[1]+1;
        if(sum/count==root.val){
            result++;
        }
        return new int[]{sum,count};
    }
}