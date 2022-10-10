package ch22_unionfind.lc805_maximum_association_set;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://www.jiuzhang.com/solutions/maximum-association-set
 * Time complexity: O(n) -- ??
 * Space complexity: O(n)
 * 
 */
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumAssociationSet(
                new String[] { "abc", "abc", "abc" },
                new String[] { "bcd", "acd", "def" })); // ["abc","acd","bcd","def"]
        System.out.println(solution.maximumAssociationSet(
                new String[] { "a", "b", "d", "e", "f" },
                new String[] { "b", "c", "e", "g", "g" })); // [d, e, f, g]
    }

    public List<String> maximumAssociationSet(String[] ListA, String[] ListB) {

        Map<String, Set<String>> graph = this.createAdjacencyList(ListA, ListB);

        Set<String> visited = new HashSet<>();
        Set<String> result = new HashSet<>();
        for (int i = 0; i < ListA.length; i++) {
            if (!visited.contains(ListA[i])) {
                Set<String> newResult = bfs(ListA[i], graph, visited);
                if (result.size() < newResult.size()) {
                    result = newResult;
                }
            }
        }
        return new ArrayList<>(result);
    }

    private Set<String> bfs(
            String start,
            Map<String, Set<String>> graph,
            Set<String> visited) {

        Set<String> result = new HashSet<>();

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            String node = queue.poll();
            result.add(node);
            for (String neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return result;
    }

    private Map<String, Set<String>> createAdjacencyList(String[] listA, String[] listB) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (int i = 0; i < listA.length; i++) {
            if (!graph.containsKey(listA[i])) {
                graph.put(listA[i], new HashSet<>());
            }
            graph.get(listA[i]).add(listB[i]);
            if (!graph.containsKey(listB[i])) {
                graph.put(listB[i], new HashSet<>());
            }
            graph.get(listB[i]).add(listA[i]);
        }
        return graph;
    }

}
