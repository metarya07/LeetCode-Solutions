/*
 * LeetCode Problem #3886: Sum of Sortable Integers
 * URL: https://leetcode.com/problems/sum-of-sortable-integers/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 149
 * Memory: 72820000
 * Submission Date: 2026-10-20 01:42:48 UTC
 * Submission ID: 2176671726
 */

class Solution {

    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        List<Integer> fac = new ArrayList<>();

        for (int x = 1; x * x <= n; x++) {
            if (n % x == 0) {
                fac.add(x);
                if (n / x != x) {
                    fac.add(n / x);
                }
            }
        }

        int[] target = nums.clone();
        Arrays.sort(target);

        int ans = 0;

        for (int d : fac) {
            boolean allGood = true;
            for (int left = 0; left < n; left += d) {
                int right = left + d - 1;
                int[] sub1 = Arrays.copyOfRange(nums, left, right + 1);
                int[] sub2 = Arrays.copyOfRange(target, left, right + 1);

                if (!check(sub1, sub2)) {
                    allGood = false;
                    break;
                }
            }
            if (allGood) {
                ans += d;
            }
        }

        return ans;
    }

    private boolean check(int[] nums1, int[] nums2) {
        int[] doubled = new int[nums1.length * 2];
        System.arraycopy(nums1, 0, doubled, 0, nums1.length);
        System.arraycopy(nums1, 0, doubled, nums1.length, nums1.length);

        return kmp(doubled, nums2);
    }

    private boolean kmp(int[] text, int[] pattern) {
        int n = text.length,
            m = pattern.length;
        int[] pi = new int[m];
        int j = 0;

        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                j++;
            }
            pi[i] = j;
        }

        j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (text[i] == pattern[j]) {
                j++;
            }
            if (j == m) {
                return true;
            }
        }
        return false;
    }
}