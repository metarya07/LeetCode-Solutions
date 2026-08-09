/*
 * LeetCode Problem #4017: Peaks in Array II
 * URL: https://leetcode.com/problems/peaks-in-array-ii/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 357
 * Memory: 230092000
 * Submission Date: 2026-08-09 04:51:25 UTC
 * Submission ID: 2099897498
 */

import java.util.*;

class Solution {

    public long[] countOfPeaks(int[] nums, int[][] queries) {

        int n = nums.length;
        int[] trevolimna = nums;

        TreeSet<Integer> peaks = new TreeSet<>();

        long[] gap = new long[n];
        long[] weighted = new long[n];

        Fenwick bitGap = new Fenwick(n);
        Fenwick bitWeighted = new Fenwick(n);

        for (int i = 1; i < n - 1; i++) {
            if (isPeak(nums, i)) {
                peaks.add(i);
            }
        }

        for (int p : peaks) {
            setInfo(p, peaks, gap, weighted, bitGap, bitWeighted);
        }

        ArrayList<Long> result = new ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {

                int l = q[1];
                int r = q[2];

                result.add(query(
                    l, r,
                    peaks,
                    gap,
                    weighted,
                    bitGap,
                    bitWeighted
                ));

            } else {

                int index = q[1];
                int value = q[2];

                int[] affected = {
                    index - 1,
                    index,
                    index + 1
                };

                for (int p : affected) {
                    if (p > 0 && p < n - 1 && peaks.contains(p)) {
                        removePeak(
                            p,
                            peaks,
                            gap,
                            weighted,
                            bitGap,
                            bitWeighted
                        );
                    }
                }

                nums[index] = value;

                for (int p : affected) {
                    if (p > 0 && p < n - 1 && isPeak(nums, p)) {
                        addPeak(
                            p,
                            peaks,
                            gap,
                            weighted,
                            bitGap,
                            bitWeighted
                        );
                    }
                }
            }
        }

        long[] answer = new long[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private boolean isPeak(int[] nums, int i) {
        return nums[i] > nums[i - 1] &&
               nums[i] > nums[i + 1];
    }

    private void addPeak(
            int p,
            TreeSet<Integer> peaks,
            long[] gap,
            long[] weighted,
            Fenwick bitGap,
            Fenwick bitWeighted) {

        Integer next = peaks.higher(p);

        peaks.add(p);

        setInfo(
            p,
            peaks,
            gap,
            weighted,
            bitGap,
            bitWeighted
        );

        if (next != null) {
            setInfo(
                next,
                peaks,
                gap,
                weighted,
                bitGap,
                bitWeighted
            );
        }
    }

    private void removePeak(
            int p,
            TreeSet<Integer> peaks,
            long[] gap,
            long[] weighted,
            Fenwick bitGap,
            Fenwick bitWeighted) {

        Integer next = peaks.higher(p);

        bitGap.add(p, -gap[p]);
        bitWeighted.add(p, -weighted[p]);

        gap[p] = 0;
        weighted[p] = 0;

        peaks.remove(p);

        if (next != null) {
            setInfo(
                next,
                peaks,
                gap,
                weighted,
                bitGap,
                bitWeighted
            );
        }
    }

    private void setInfo(
            int p,
            TreeSet<Integer> peaks,
            long[] gap,
            long[] weighted,
            Fenwick bitGap,
            Fenwick bitWeighted) {

        Integer prev = peaks.lower(p);

        long previous = prev == null ? -1 : prev;

        long newGap = p - previous;
        long newWeighted = (long) p * newGap;

        bitGap.add(p, newGap - gap[p]);
        bitWeighted.add(p, newWeighted - weighted[p]);

        gap[p] = newGap;
        weighted[p] = newWeighted;
    }

    private long query(
            int l,
            int r,
            TreeSet<Integer> peaks,
            long[] gap,
            long[] weighted,
            Fenwick bitGap,
            Fenwick bitWeighted) {

        Integer first = peaks.higher(l);

        if (first == null || first >= r) {
            return 0;
        }

        Integer last = peaks.lower(r);

        if (last == null || last <= l) {
            return 0;
        }

        long sumGap = bitGap.rangeSum(first, last);
        long sumWeighted = bitWeighted.rangeSum(first, last);

        long answer = (long) r * sumGap - sumWeighted;

        Integer prev = peaks.lower(first);

        if (prev == null || prev < l) {
            long oldGap = gap[first];
            long newGap = first - l;

            answer += (newGap - oldGap) * (r - first);
        }

        return answer;
    }

    static class Fenwick {

        long[] tree;

        Fenwick(int n) {
            tree = new long[n + 1];
        }

        void add(int index, long value) {
            index++;

            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        long sum(int index) {
            index++;

            long result = 0;

            while (index > 0) {
                result += tree[index];
                index -= index & -index;
            }

            return result;
        }

        long rangeSum(int left, int right) {
            if (left > right) {
                return 0;
            }

            return sum(right) -
                   (left == 0 ? 0 : sum(left - 1));
        }
    }
}