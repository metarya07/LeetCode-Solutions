/*
 * LeetCode Problem #301: Remove Invalid Parentheses
 * URL: https://leetcode.com/problems/remove-invalid-parentheses/
 * Solution #2 (java)
 * Status: Accepted
 * Runtime: 0 ms
 * Memory: 43.9 MB
 * Submission Date: 2026-10-07 03:12:18 UTC
 * Submission ID: 2164833243
 */

import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        return new AbstractList<String>() {
            private int minRemoved;
            private int[] temp;
            private Set<String> ans;
            private List<String> finalAns;
            private int totalOpen;
            private int totalClose;

            public String get(int index) {
                if (finalAns == null) solve(s);
                return finalAns.get(index);
            }

            public int size() {
                if (finalAns == null) solve(s);
                return finalAns.size();
            }

            private void solve(String s) {
                ans = new HashSet<>();
                totalOpen = 0;
                totalClose = 0;
                minRemoved = s.length();
                temp = new int[s.length()];
                for (int i = 0; i < s.length(); i++) {
                    temp[i] = s.charAt(i);
                    if (s.charAt(i) == '(') totalOpen++;
                    else if (s.charAt(i) == ')') totalClose++;
                }
                find(totalOpen, totalClose, 0, 0);
                finalAns = new ArrayList<>(ans);
            }

            private boolean valid() {
                int o = 0, c = 0;
                for (int num : temp) {
                    if (num == -1) continue;
                    char ch = (char) num;
                    if (ch == '(') o++;
                    if (ch == ')') c++;
                    if (c > o) return false;
                }
                return o == c;
            }

            private void add(int removed) {
                if (minRemoved < removed) return;
                StringBuilder sb = new StringBuilder();
                for (int num : temp) {
                    if (num == -1) continue;
                    sb.append((char) num);
                }
                if (minRemoved > removed) {
                    ans.clear();
                    minRemoved = removed;
                }
                ans.add(sb.toString());
            }

            private void find(int open, int close, int removed, int start) {
                if (open < 0 || close < 0 || removed > minRemoved) return;
                if (open == close && valid()) {
                    add(removed);
                    return;
                }
                for (int i = start; i < temp.length; i++) {
                    if (temp[i] == -1) continue;
                    char ch = (char) temp[i];
                    if (ch == '(' && (i == 0 || (char) temp[i - 1] != '(' || temp[i - 1] == -1)) {
                        temp[i] = -1;
                        find(open - 1, close, removed + 1, i);
                        temp[i] = ch;
                    } else if (ch == ')' && (i == 0 || (char) temp[i - 1] != ')' || temp[i - 1] == -1)) {
                        temp[i] = -1;
                        find(open, close - 1, removed + 1, i);
                        temp[i] = ch;
                    }
                }
            }
        };
    }
}