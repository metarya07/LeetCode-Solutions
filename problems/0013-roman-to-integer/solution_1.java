/*
 * LeetCode Problem #13: Roman to Integer
 * URL: https://leetcode.com/problems/roman-to-integer/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 4
 * Memory: 46572000
 * Submission Date: 2026-07-20 14:43:57 UTC
 * Submission ID: 2074688379
 */

class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;

        for (int i = 0; i < s.length(); i++){
            int current = map.get(s.charAt(i));
            if(i<s.length() - 1 && current < map.get(s.charAt(i +1))){
                total -= current;
            } else {
                total += current;
            }
        }

        return total;
  
    }
}