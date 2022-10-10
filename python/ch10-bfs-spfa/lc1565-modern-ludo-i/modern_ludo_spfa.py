"""https://www.jiuzhang.com/solutions/modern-ludo-i"""


from collections import deque


class Solution:
    """SPFA"""

    def modern_ludo(self, length, connections):
        graph = self.build_graph(length, connections)

        queue = deque([1])
        distance = {i: float("inf") for i in range(1, length + 1)}
        distance[1] = 0

        while queue:
            node = queue.popleft()
            for next_node in graph[node]:
                if distance[next_node] > distance[node]:
                    distance[next_node] = distance[node]
            # ATTN a dice has 6 sides. "+ 7" is for the end of the range exclusive
            for next_node in range(node + 1, min(node + 7, length + 1)):
                if distance[next_node] > distance[node] + 1:
                    distance[next_node] = distance[node] + 1
                    queue.append(next_node)
        return distance[length]

    def build_graph(self, length, connections):
        """util to convert connections to an adjacency list"""
        graph = {i: set() for i in range(1, length + 1)}
        for connection in connections:
            graph[connection[0]].add(connection[1])
        return graph


solution = Solution()
print(solution.modern_ludo(10, [[2, 10]]))  # 1
print(solution.modern_ludo(15, [[2, 8], [6, 9]]))  # 2
