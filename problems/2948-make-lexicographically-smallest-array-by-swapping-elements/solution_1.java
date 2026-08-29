/*
 * LeetCode Problem #2948: Make Lexicographically Smallest Array by Swapping Elements
 * URL: https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 93
 * Memory: 227864000
 * Submission Date: 2026-08-29 03:39:45 UTC
 * Submission ID: 2123408118
 */

class Solution {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int[] numsSorted = new int[nums.length];
        for (int i = 0; i < nums.length; i++) numsSorted[i] = nums[i];
        Arrays.sort(numsSorted);

        int currGroup = 0;
        HashMap<Integer, Integer> numToGroup = new HashMap<>();
        numToGroup.put(numsSorted[0], currGroup);

        HashMap<Integer, LinkedList<Integer>> groupToList = new HashMap<>();
        groupToList.put(
            currGroup,
            new LinkedList<Integer>(Arrays.asList(numsSorted[0]))
        );

        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(numsSorted[i] - numsSorted[i - 1]) > limit) {
                // new group
                currGroup++;
            }

            // assign current element to group
            numToGroup.put(numsSorted[i], currGroup);

            // add element to sorted group list
            if (!groupToList.containsKey(currGroup)) {
                groupToList.put(currGroup, new LinkedList<Integer>());
            }
            groupToList.get(currGroup).add(numsSorted[i]);
        }

        // iterate through input and overwrite each element with the next element in its corresponding group
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int group = numToGroup.get(num);
            nums[i] = groupToList.get(group).pop();
        }

        return nums;
    }
}