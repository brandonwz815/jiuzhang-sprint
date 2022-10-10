package ch10_bfs_spfa.lc573_build_post_office_ii;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * https://www.jiuzhang.com/solutions/build-post-office-ii
 * Time complexity: O(nnmm)
 * 
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.shortestDistance(new int[][] {
                { 0, 1, 0, 0, 0 },
                { 1, 0, 0, 2, 1 },
                { 0, 1, 0, 0, 0 }
        })); // 8
        System.out.println(solution.shortestDistance(new int[][] {
                { 0, 1, 0 },
                { 1, 0, 1 },
                { 0, 1, 0 }
        })); // 4
    }

    public int[] deltaX = { 0, 1, -1, 0 };
    public int[] deltaY = { 1, 0, 0, -1 };

    private int shortestDistance(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == GridType.EMPTY) {
                    Map<Integer, Integer> distances = bfs(grid, i, j);
                    minDistance = Math.min(minDistance, getDistanceSum(distances, grid));
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private Map<Integer, Integer> bfs(int[][] grid, int row, int col) {
        int cols = grid[0].length;

        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> distances = new HashMap<>();

        int current = row * cols + col;
        queue.offer(current);
        distances.put(current, 0);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            int x = node / cols, y = node % cols;
            for (int i = 0; i < 4; i++) {
                int adjX = x + deltaX[i];
                int adjY = y + deltaY[i];
                if (!this.isValid(adjX, adjY, grid)) {
                    continue;
                }
                int next = adjX * cols + adjY;
                if (distances.containsKey(next)) {
                    continue;
                }
                distances.put(next, distances.get(node) + 1);
                if (grid[adjX][adjY] != GridType.HOUSE) {
                    queue.add(next);
                }
            }
        }

        return distances;
    }

    private boolean isValid(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return false;
        }
        return grid[x][y] == GridType.EMPTY || grid[x][y] == GridType.HOUSE;
    }

    private int getDistanceSum(Map<Integer, Integer> distances, int[][] grid) {
        int distanceSum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == GridType.HOUSE) {
                    int key = i * grid[0].length + j;
                    if (!distances.containsKey(key)) {
                        return Integer.MAX_VALUE;
                    }
                    distanceSum += distances.get(key);
                }
            }
        }
        return distanceSum;
    }

}

class GridType {
    public static int EMPTY = 0;
    public static int HOUSE = 1;
    public static int WALL = 2;
}
