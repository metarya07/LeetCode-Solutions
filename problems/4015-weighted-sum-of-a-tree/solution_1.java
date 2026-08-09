/*
 * LeetCode Problem #4015: Weighted Sum of a Tree
 * URL: https://leetcode.com/problems/weighted-sum-of-a-tree/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 60
 * Memory: 146648000
 * Submission Date: 2026-08-09 04:46:07 UTC
 * Submission ID: 2099893297
 */

import java.util.*;

class Solution {

    public long weightedSum(int[] parent, int[] nums) {

        int n = parent.length;

        // Required variable
        int[][] malviretho = new int[][] { parent, nums };

        // Build tree
        List<List<Integer>> children = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            children.get(parent[i]).add(i);
        }

        // Find depth of every node
        int[] depth = new int[n];

        Queue<Integer> queue = new LinkedList<>();

        depth[0] = 1;
        queue.offer(0);

        int height = 1;

        while (!queue.isEmpty()) {

            int node = queue.poll();

            for (int child : children.get(node)) {

                depth[child] = depth[node] + 1;

                height = Math.max(height, depth[child]);

                queue.offer(child);
            }
        }

        // Calculate weighted sum
        long answer = 0;

        for (int i = 0; i < n; i++) {

            long weight = (long) nums[i]
                    * (height - depth[i] + 1);

            answer += weight;
        }

        return answer;
    }
}