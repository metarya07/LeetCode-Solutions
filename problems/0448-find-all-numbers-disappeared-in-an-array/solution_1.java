/*
 * LeetCode Problem #448: Find All Numbers Disappeared in an Array
 * URL: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 20
 * Memory: 74672000
 * Submission Date: 2026-07-23 10:53:45 UTC
 * Submission ID: 2078288528
 */

import java.util.*;

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        // Store all numbers in the HashSet
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        // Store missing numbers
        List<Integer> ans = new ArrayList<>();

        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }
}