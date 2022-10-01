"""https://www.jiuzhang.com/solutions/modern-ludo-i"""
from collections import deque


class Solution:
    def modern_ludo(self, length, connections):
        """main logic starts"""

        graph = self.build_graph(length, connections)
        queue = deque([1])
        distance = {1: 0}

        while queue:
            node = queue.popleft()
            for neighbor in range(node + 1, min(node + 7, length + 1)):
                connected_nodes = self.get_unvisited_nodes(graph, distance, neighbor)
                for connected_node in connected_nodes:
                    queue.append(connected_node)  # ATTN append, not "pushright"
                    distance[connected_node] = distance[node] + 1
        return distance[length]

    def get_unvisited_nodes(self, graph, distance, node):
        """private method"""

        queue = deque([node])
        unvisited_nodes = set()

        while queue:
            node = queue.popleft()
            if node not in distance:
                unvisited_nodes.add(node)
                for neighbor in graph[node]:
                    if neighbor not in distance:
                        queue.append(neighbor)
                        unvisited_nodes.add(neighbor)
        return unvisited_nodes

    def build_graph(self, length, connections):
        """build an ajacency list"""
        graph = {i: set() for i in range(1, length + 1)}
        for a, b in connections:
            graph[a].add(b)
        return graph


solution = Solution()
print(solution.modern_ludo(10, [[2, 10]]))  # 1
print(solution.modern_ludo(15, [[2, 8], [6, 9]]))  # 2
