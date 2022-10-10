package ch22_unionfind.lc1463_paper_review;

import java.util.Arrays;
import java.util.List;

import lintcode.UnionFind;

/**
 * https://www.jiuzhang.com/solutions/paper-review
 * Time complexity: O(nm)
 * Space complexity: (m) with a rolling array
 * 
 */
public class SolutionUF {

    public static void main(String[] args) {
        SolutionUF solution = new SolutionUF();
        System.out.println(solution.getSimilarity(
                Arrays.asList(new String[] { "great", "acting", "skills", "life" }),
                Arrays.asList(new String[] { "fine", "drama", "talent", "health" }),
                Arrays.asList(
                        Arrays.asList("great", "good"),
                        Arrays.asList("fine", "good"),
                        Arrays.asList("acting", "drama"),
                        Arrays.asList("skills", "talent")))); // 0.75
    }

    public float getSimilarity(
            List<String> words1,
            List<String> words2,
            List<List<String>> pairs) {

        UnionFind<String> uf = new UnionFind<>();

        for (String word : words1) {
            uf.add(word);
        }
        for (String word : words2) {
            uf.add(word);
        }
        for (List<String> pair : pairs) {
            uf.add(pair.get(0));
            uf.add(pair.get(1));
            uf.merge(pair.get(0), pair.get(1));
        }

        float similarity = getLCS(uf, words1, words2);
        float total = words1.size() + words2.size();
        return 2 * similarity / total;
    }

    private float getLCS(
            UnionFind<String> uf,
            List<String> words1,
            List<String> words2) {

        int rows = words1.size();
        int cols = words2.size();

        int[][] dp = new int[2][cols + 1]; // rolling array

        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                if (uf.isConnected(words1.get(i - 1), words2.get(j - 1))) {
                    dp[i % 2][j] = dp[i % 2][j - 1] + 1;
                } else {
                    dp[i % 2][j] = Math.max(
                            dp[(i - 1) % 2][j],
                            dp[i % 2][j - 1]);
                }
            }
        }

        return dp[rows % 2][cols];
    }

}
