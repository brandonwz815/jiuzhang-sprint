package ch22_unionfind.lc434_number_of_islands_ii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lintcode.Point;

/**
 * https://www.jiuzhang.com/solutions/number-of-islands-ii
 * 
 */
public class Solution2 {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        Point[] operators = new Point[] {
                new Point(1, 1),
                new Point(0, 1),
                new Point(3, 3),
                new Point(3, 4)
        };
        System.out.println(solution.numIslands2(4, 5, operators)); // [1,1,2,2]

        Point[] operators2 = new Point[] {
                new Point(0, 0),
                new Point(0, 1),
                new Point(2, 2),
                new Point(2, 1)
        };
        System.out.println(solution.numIslands2(3, 3, operators2)); // [1,1,2,2]

    }

    int converttoId(int x, int y, int m) {
        return x * m + y;
    }

    class UnionFind {
        HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();

        UnionFind(int n, int m) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int id = converttoId(i, j, m);
                    father.put(id, id);
                }
            }
        }

        int compressed_find(int x) {
            int parent = father.get(x);
            while (parent != father.get(parent)) {
                parent = father.get(parent);
            }
            int temp = -1;
            int fa = x;
            while (fa != father.get(fa)) {
                temp = father.get(fa);
                father.put(fa, parent);
                fa = temp;
            }
            return parent;
        }

        void union(int x, int y) {
            int fa_x = compressed_find(x);
            int fa_y = compressed_find(y);
            if (fa_x != fa_y)
                father.put(fa_x, fa_y);
        }
    }

    /**
     * @param n         an integer
     * @param m         an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> ans = new ArrayList<Integer>();
        if (operators == null) {
            return ans;
        }

        int[] dx = { 0, -1, 0, 1 };
        int[] dy = { 1, 0, -1, 0 };
        int[][] island = new int[n][m];

        UnionFind uf = new UnionFind(n, m);
        int count = 0;

        for (int i = 0; i < operators.length; i++) {
            int x = operators[i].x;
            int y = operators[i].y;
            if (island[x][y] != 1) {
                count++;
                island[x][y] = 1;
                int id = converttoId(x, y, m);
                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if (0 <= nx && nx < n && 0 <= ny && ny < m && island[nx][ny] == 1) {
                        int nid = converttoId(nx, ny, m);
                        int fa = uf.compressed_find(id);
                        int nfa = uf.compressed_find(nid);
                        if (fa != nfa) {
                            count--;
                            uf.union(id, nid);
                        }
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}
