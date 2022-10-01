"""
https://www.lintcode.com/problem/1852
"""


class Solution:
    """
    @param prices: a list of integer
    @return: return the actual prices
    """

    def final_discounted_price(self, prices):
        """brute-force"""
        results = list(prices)
        for i, price in enumerate(prices):
            for j in range(i + 1, len(prices)):
                if prices[j] <= price:
                    results[i] = price - prices[j]
                    break
        return results


solution = Solution()
print(solution.final_discounted_price([2, 3, 1, 2, 4, 2]))

# Time complexity: O(n^2)
# Space complexity: O(n)
