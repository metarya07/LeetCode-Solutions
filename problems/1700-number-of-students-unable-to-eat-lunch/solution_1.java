/*
 * LeetCode Problem #1700: Number of Students Unable to Eat Lunch
 * URL: https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 0
 * Memory: 43008000
 * Submission Date: 2026-08-01 05:15:11 UTC
 * Submission ID: 2089489322
 */

class Solution {
    public int countStudents(int[] students, int[] sandwiches) {

        int count0 = 0;
        int count1 = 0;

        for (int i = 0; i < students.length; i++) {
            if (students[i] == 0)
                count0++;
            else
                count1++;
        }

        for (int i = 0; i < sandwiches.length; i++) {

            if (sandwiches[i] == 0) {

                if (count0 == 0)
                    return sandwiches.length - i;

                count0--;

            } else {

                if (count1 == 0)
                    return sandwiches.length - i;

                count1--;
            }
        }

        return 0;
    }
}