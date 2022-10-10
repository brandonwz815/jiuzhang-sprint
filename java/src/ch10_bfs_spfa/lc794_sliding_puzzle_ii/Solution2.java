package ch10_bfs_spfa.lc794_sliding_puzzle_ii;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://www.jiuzhang.com/solutions/sliding-puzzle-ii
 * Use a bi-directional BFS
 * Time complexity: O(T^1/2)
 */
public class Solution2 {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
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

        if (source.equals(target)) {
            return 0;
        }

        Queue<String> forwardQueue = new ArrayDeque<>();
        Set<String> forwardSet = new HashSet<>();
        forwardQueue.offer(source);
        forwardSet.add(source);

        Queue<String> backwardQueue = new ArrayDeque<>();
        Set<String> backwardSet = new HashSet<>();
        backwardQueue.offer(target);
        backwardSet.add(target);

        int steps = 0;
        while (!forwardQueue.isEmpty() && !backwardQueue.isEmpty()) {
            steps++;
            if (extendQueue(forwardQueue, forwardSet, backwardSet)) {
                return steps;
            }

            steps++;
            if (extendQueue(backwardQueue, backwardSet, forwardSet)) {
                return steps;
            }
        }
        return -1;
    }

    private boolean extendQueue(Queue<String> queue, Set<String> set, Set<String> targetSet) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            String curt = queue.poll();
            for (String next : getNext(curt)) {
                if (set.contains(next)) {
                    continue;
                }
                if (targetSet.contains(next)) {
                    return true;
                }
                queue.offer(next);
                set.add(next);
            }
        }
        return false;
    }

    private List<String> getNext(String curt) {
        List<String> states = new ArrayList<>();

        int zeroIdx = curt.indexOf('0');
        int x = zeroIdx / 3;
        int y = zeroIdx % 3;
        for (int i = 0; i < 4; i++) {
            int x_ = x + dx[i];
            int y_ = y + dy[i];
            if (x_ >= 0 && x_ < 3 && y_ >= 0 && y_ < 3) {
                int next = x_ * 3 + y_;
                char[] chars = curt.toCharArray();
                chars[zeroIdx] = chars[next];
                chars[next] = '0';
                states.add(new String(chars));
            }
        }

        return states;
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

}
