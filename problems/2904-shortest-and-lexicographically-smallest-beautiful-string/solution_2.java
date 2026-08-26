/*
 * LeetCode Problem #2904: Shortest and Lexicographically Smallest Beautiful String
 * URL: https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/
 * Solution #2 (Java)
 * Status: Accepted
 * Runtime: 1
 * Memory: 43920000
 * Submission Date: 2026-08-26 02:47:49 UTC
 * Submission ID: 2120287259
 */

public class Solution {

    public String shortestBeautifulSubstring(String s, int k) {
        char[] input = s.toCharArray();

        int head = -1;
        int tail = -1;

        int back = 0;
        int countOnes = 0;

        for (int front = 0; front < input.length; ++front) {
            countOnes += input[front] - '0';
            if (countOnes < k) {
                continue;
            }

            while (back < front && input[back] == '0') {
                countOnes -= input[back] - '0';
                ++back;
            }

            if (head == -1 || head - tail + 1 > front - back + 1) {
                head = front;
                tail = back;
            } else if (head - tail + 1 == front - back + 1
                    && s.substring(tail, head + 1).compareTo(s.substring(back, front + 1)) > 0) {
                head = front;
                tail = back;
            }
            while (back < front && countOnes == k) {
                countOnes -= input[back] - '0';
                ++back;
            }
        }
        return head != -1 ? s.substring(tail, head + 1) : "";
    }
}