from heapq import heappush, heappop


class Heap:

    def __init__(self):
        self.minheap = []
        self.deleted_set = set()

    def push(self, index, val):
        heappush(self.minheap, (val, index))

    def _lazy_deletion(self):
        while self.minheap and self.minheap[0][1] in self.deleted_set:
            heappop(self.minheap)

    def top(self):
        self._lazy_deletion()
        return self.minheap[0]

    def pop(self):
        self._lazy_deletion()
        heappop(self.minheap)

    def delete(self, index):
        self.deleted_set.add(index)

    def is_empty(self):
        return not bool(self.minheap)


class Solution:
    """
    @param A: the array
    @param K: sum
    @return: the length
    """

    def shortest_subarray(self, A, K):
        prefix_sum = self.get_prefix_sum(A)

        # do binary search to find the minimum length that
        # we could find a subarray within that length and sum >= K
        start, end = 1, len(A)
        while start + 1 < end:
            mid = (start + end) // 2
            if self.is_valid(prefix_sum, K, mid):
                end = mid
            else:
                start = mid
        if self.is_valid(prefix_sum, K, start):
            return start
        if self.is_valid(prefix_sum, K, end):
            return end
        return -1

    @staticmethod
    def is_valid(prefix_sum, K, length):
        minheap = Heap()
        for end in range(len(prefix_sum)):
            index = end - length - 1
            minheap.delete(index)
            # find the maximum subarray
            if not minheap.is_empty() and prefix_sum[end] - minheap.top()[0] >= K:
                return True
            minheap.push(end, prefix_sum[end])
        return False

    @staticmethod
    def get_prefix_sum(A):
        prefix_sum = [0]
        for num in A:
            prefix_sum.append(prefix_sum[-1] + num)
        return prefix_sum


if __name__ == '__main__':
    s = Solution()
    t = s.shortest_subarray([4, -1, 2, 3, -2], 8)
    print(t)
