/*
 * LeetCode Problem #1606: Find Servers That Handled Most Number of Requests
 * URL: https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 122
 * Memory: 104064000
 * Submission Date: 2026-11-09 04:19:06 UTC
 * Submission ID: 2192239433
 */

class Solution {
    record Job(int at, int server) {}  // at: the time its server frees up

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<Integer> free = new PriorityQueue<>(); 
        for (int x = 0; x < k; x++) free.add(x);  // at request 0, server x's next turn is x
        PriorityQueue<Job> busy = new PriorityQueue<>(Comparator.comparingInt(Job::at));

        int[] handled = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek().at() <= arrival[i]) {  // done by the arrival
                int x = busy.poll().server();
                free.add(i + Math.floorMod(x - i, k));  // its first turn from i on
            }
            if (free.isEmpty()) continue;  // none free: the request is dropped
            int x = free.poll() % k;
            busy.add(new Job(arrival[i] + load[i], x));
            handled[x]++;
        }

        int most = Arrays.stream(handled).max().getAsInt();
        return IntStream.range(0, k).filter(x -> handled[x] == most).boxed().toList();
    }
}