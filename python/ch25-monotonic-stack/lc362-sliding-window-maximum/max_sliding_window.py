"""
https://www.lintcode.com/problem/362
"""
from typing import (
    List,
)


class Solution:
    """
    @param nums: A list of integers.
    @param k: An integer
    @return: The maximum number inside the window at each moving.
    """

    def max_sliding_window(self, nums: List[int], k: int) -> List[int]:
        """brute-force"""
        if not nums:
            return []

        results = []
        for i in range(len(nums) - k + 1):
            results.append(max(nums[i : i + k]))
        return results


solution = Solution()
print(solution.max_sliding_window([1, 2, 3, 1, 2, 3], 5))
