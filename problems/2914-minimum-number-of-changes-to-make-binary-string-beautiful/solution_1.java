/*
 * LeetCode Problem #2914: Minimum Number of Changes to Make Binary String Beautiful
 * URL: https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 6
 * Memory: 46092000
 * Submission Date: 2026-11-18 15:01:22 UTC
 * Submission ID: 2198758314
 */

class Solution {

    public int minChanges(String s) {
        // Initialize with first character of string
        char currentChar = s.charAt(0);

        int consecutiveCount = 0;
        int minChangesRequired = 0;

        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            // If current character matches the previous sequence
            if (s.charAt(i) == currentChar) {
                consecutiveCount++;
                continue;
            }

            // If we have even count of characters, start new sequence
            if (consecutiveCount % 2 == 0) {
                consecutiveCount = 1;
            }
            // If odd count, we need to change current character
            // to match previous sequence
            else {
                consecutiveCount = 0;
                minChangesRequired++;
            }

            // Update current character for next iteration
            currentChar = s.charAt(i);
        }

        return minChangesRequired;
    }
}