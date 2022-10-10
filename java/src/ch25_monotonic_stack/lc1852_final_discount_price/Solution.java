package ch25_monotonic_stack.lc1852_final_discount_price;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * monotonic stack
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] results = solution.finalDiscountPrice(new int[] { 2, 3, 1, 2, 4, 2 });
        System.out.println(Arrays.toString(results)); // [1, 2, 1, 0, 2, 2]

        int[] results2 = solution.finalDiscountPrice(new int[] { 1, 2, 3, 4, 5 });
        System.out.println(Arrays.toString(results2)); // [1, 2, 3, 4, 5]
    }

    public int[] finalDiscountPrice(int[] prices) {
        int[] results = Arrays.copyOf(prices, prices.length);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peekLast()] - prices[i] >= 0) {
                results[stack.peekLast()] = prices[stack.peekLast()] - prices[i];
                stack.pollLast();
            }
            stack.addLast(i);
        }
        return results;
    }
}
