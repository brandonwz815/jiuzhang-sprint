"""
https://www.jiuzhang.com/solutions/number-of-islands
"""

from collections import deque


DIRECTIONS = [(1, 0), (0, -1), (-1, 0), (0, 1)]


class Solution:
    """
    @param grid: a boolean 2D matrix
    @return: an integer
    """

    def num_islands(self, grid):
        """traversal with BFS"""

        islands, n, m = 0, len(grid), len(grid[0])
        visited = set()
        for i in range(n):
            for j in range(m):
                if grid[i][j] and (i, j) not in visited:
                    self.bfs(grid, visited, i, j)
                    islands += 1
        return islands

    def bfs(self, grid, visited, x, y):
        """bfs"""
        queue = deque([(x, y)])
        visited.add((x, y))
        while queue:
            x, y = queue.popleft()
            for delta_x, delta_y in DIRECTIONS:
                next_x = x + delta_x
                next_y = y + delta_y
                if self.is_valid(grid, visited, next_x, next_y):
                    queue.append((next_x, next_y))
                    visited.add((next_x, next_y))

    def is_valid(self, grid, visited, x, y):
        """check if (x, y) is out of bounds or already visited"""
        n, m = len(grid), len(grid[0])
        if not (0 <= x < n and 0 <= y < m):
            return False
        if (x, y) in visited:
            return False
        return grid[x][y]


solution = Solution()
print(
    solution.num_islands(
        [
            [1, 1, 0, 0, 0],
            [0, 1, 0, 0, 1],
            [0, 0, 0, 1, 1],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 1],
        ]
    )
)
print(solution.num_islands([[1,1]]))
