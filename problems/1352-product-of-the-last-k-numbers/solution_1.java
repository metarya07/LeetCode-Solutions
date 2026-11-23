/*
 * LeetCode Problem #1352: Product of the Last K Numbers
 * URL: https://leetcode.com/problems/product-of-the-last-k-numbers/
 * Solution #1 (Java)
 * Status: Accepted
 * Runtime: 2475
 * Memory: 83836000
 * Submission Date: 2026-11-23 03:43:55 UTC
 * Submission ID: 2201627205
 */

class ProductOfNumbers {
    List<Integer> a = new ArrayList<>();

    public ProductOfNumbers() {}

    public void add(int num) {
        a.add(num);
    }

    public int getProduct(int k) {
        int n = a.size(), prod = 1;
        for (int i = n - k; i < n; i++)
            prod *= a.get(i);
        return prod;
    }
}