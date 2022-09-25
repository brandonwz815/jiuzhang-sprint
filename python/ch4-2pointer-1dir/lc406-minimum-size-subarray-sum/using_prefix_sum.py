from typing import (
    List,
)


class Solution:
    """
    @param nums: an array of integers
    @param s: An integer
    @return: an integer representing the minimum size of subarray
    """

    def minimum_size(self, nums: List[int], s: int) -> int:
        # write your code here
        n = len(nums)
        prefix_sum = self.get_prefix_sum(nums)
        min_length = float("inf")
        for start in range(n):
            for end in range(start, n):
                sum_of_subarray = prefix_sum[end + 1] - prefix_sum[start]
                if sum_of_subarray >= s:
                    min_length = min(min_length, end - start + 1)

        if min_length == float("inf"):
            return -1
        return min_length

    def get_prefix_sum(self, nums: List[int]) -> List[int]:
        n = len(nums)
        prefix_sum = [0]
        for i in range(1, n + 1):
            prefix_sum.append(prefix_sum[i - 1] + nums[i - 1])
        return prefix_sum


solution = Solution()
print(solution.minimum_size([2, 3, 1, 2, 4, 3], 7))


# Don't forget n+1 on line 31 "for i in range(1, n + 1):"
# Dont' forget range on line 19 "for end in range(start, n):"
