/*
 * LeetCode Problem #3310: Remove Methods From Project
 * URL: https://leetcode.com/problems/remove-methods-from-project/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 48
 * Memory: 292136000
 * Submission Date: 2026-08-05 02:21:14 UTC
 * Submission ID: 2094701211
 */

class Solution {

    List<Integer>[] graph;
    boolean[] suspicious;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        suspicious = new boolean[n];

        dfs(k);

        for (int[] edge : invocations) {

            int u = edge[0];
            int v = edge[1];

            if (!suspicious[u] && suspicious[v]) {

                List<Integer> ans = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }

                return ans;
            }
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    private void dfs(int node) {

        suspicious[node] = true;

        for (int next : graph[node]) {

            if (!suspicious[next]) {
                dfs(next);
            }

        }
    }
}