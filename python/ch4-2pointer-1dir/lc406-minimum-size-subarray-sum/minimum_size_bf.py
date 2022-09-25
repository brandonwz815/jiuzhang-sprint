class Solution:

    def minimum_size(self, nums, s):
        min_length = float('inf')
        for start in range(len(nums)):
            for end in range(start, len(nums)):
                sum_of_subarray = 0
                for i in range(start, end + 1):
                    sum_of_subarray += nums[i]
                if sum_of_subarray >= s:
                    min_length = min(min_length, end - start + 1)
        if min_length == float('inf'):
            return -1
        return min_length


solution = Solution()
print(solution.minimum_size([2,3,1,2,4,3], 7))
