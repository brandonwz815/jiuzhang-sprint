package ch10_bfs_spfa.lc794_sliding_puzzle_ii;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://www.jiuzhang.com/solutions/sliding-puzzle-ii
 * Use a uni-directional BFS
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minMoveStep(
                new int[][] {
                        { 2, 8, 3 },
                        { 1, 0, 4 },
                        { 7, 6, 5 } },
                new int[][] {
                        { 1, 2, 3 },
                        { 8, 0, 4 },
                        { 7, 6, 5 } }));
        System.out.println(solution.minMoveStep(
                new int[][] {
                        { 2, 3, 8 },
                        { 7, 0, 5 },
                        { 1, 6, 4 } },
                new int[][] {
                        { 1, 2, 3 },
                        { 8, 0, 4 },
                        { 7, 6, 5 } }));
    }

    private int[] dx = { 0, 1, -1, 0 };
    private int[] dy = { 1, 0, 0, -1 };

    /**
     * @param init_state:  the initial state of chessboard
     * @param final_state: the final state of chessboard
     * @return: return an integer, denote the number of minimum moving
     */
    private int minMoveStep(int[][] initState, int[][] finalState) {
        String source = this.matrixToString(initState);
        String target = this.matrixToString(finalState);

        Queue<String> queue = new ArrayDeque<>();
        Map<String, Integer> distance = new HashMap<>();
        queue.offer(source);
        distance.put(source, 0);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (node.equals(target)) {
                return distance.get(node);
            }
            for (String next : getNext(node)) {
                if (distance.containsKey(next)) {
                    continue;
                }
                queue.offer(next);
                distance.put(next, distance.get(node) + 1);
            }
        }

        return -1;
    }

    private String matrixToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                sb.append(state[i][j]);
            }
        }
        return sb.toString();
    }

    private List<String> getNext(String state) {
        List<String> states = new ArrayList<>();

        int zeroIdx = state.indexOf('0');
        int x = zeroIdx / 3;
        int y = zeroIdx % 3;
        for (int i = 0; i < 4; i++) { // 4 is dx.length
            int x_ = x + dx[i];
            int y_ = y + dy[i];
            if (x_ >= 0 && x_ < 3 && y_ >= 0 && y_ < 3) { // 3 is grid.length
                int next = x_ * 3 + y_;
                char[] chars = state.toCharArray();
                chars[zeroIdx] = chars[next];
                chars[next] = '0';
                states.add(new String(chars));
            }
        }
        return states;
    }
}
