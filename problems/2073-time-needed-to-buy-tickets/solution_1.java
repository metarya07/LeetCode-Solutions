/*
 * LeetCode Problem #2073: Time Needed to Buy Tickets
 * URL: https://leetcode.com/problems/time-needed-to-buy-tickets/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 9
 * Memory: 46240000
 * Submission Date: 2026-08-02 07:24:03 UTC
 * Submission ID: 2091044111
 */

import java.util.*;

class Solution {

    public int timeRequiredToBuy(int[] tickets, int k) {

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < tickets.length; i++) {
            queue.offer(new int[]{i, tickets[i]});
        }

        int time = 0;

        while (!queue.isEmpty()) {

            int[] person = queue.poll();

            person[1]--;
            time++;

            if (person[1] == 0) {

                if (person[0] == k)
                    return time;

            } else {

                queue.offer(person);

            }
        }

        return time;
    }
}