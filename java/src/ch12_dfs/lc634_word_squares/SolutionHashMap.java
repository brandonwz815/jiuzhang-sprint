package ch12_dfs.lc634_word_squares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.jiuzhang.com/solutions/word-squares
 * 
 * DFS with HashMap "prefixToWords"
 * 
 * Time complexity:
 * Space complexity:
 * 
 */
public class SolutionHashMap {

    public static void main(String[] args) {
        SolutionHashMap solution = new SolutionHashMap();

        System.out.println(solution.wordSquares(
                new String[] { "area", "lead", "wall", "lady", "ball" })); // [["wall","area","lead","lady"],["ball","area","lead","lady"]]

        System.out.println(solution.wordSquares(
                new String[] { "abat", "baba", "atan", "atal" })); // [["baba","abat","baba","atan"],["baba","abat","baba","atal"]]
    }

    public List<List<String>> wordSquares(String[] words) {
        Map<String, List<String>> prefixToWords = getPrefixToWords(words);
        List<List<String>> squares = new ArrayList<>(words[0].length());
        for (String word : words) {
            List<String> currentSquare = new ArrayList<>();
            currentSquare.add(word);
            search(prefixToWords, currentSquare, squares);
        }
        return squares;
    }

    private void search(
            Map<String, List<String>> prefixToWords,
            List<String> currentSquare,
            List<List<String>> squares) {
        int currentRows = currentSquare.size();
        if (currentRows == currentSquare.get(0).length()) { // aka rows == cols
            squares.add(new ArrayList<>(currentSquare)); // ATTN use new ArrayList<>()
        } else {

            /*
             * ATTN pruning for optimization
             * Use two for this 2D matrix
             * The outer loop iterates over columns of currentSquare
             * the inner loop iterates over rows currentSquare
             */
            for (int i = 0; i < currentRows; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < currentRows; j++) {
                    sb.append(currentSquare.get(j).charAt(currentRows));
                }
                if (!prefixToWords.containsKey(sb.toString())) {
                    return;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < currentRows; i++) {
                sb.append(currentSquare.get(i).charAt(currentRows));
            }
            String prefix = sb.toString();

            List<String> words = prefixToWords.getOrDefault(prefix, new ArrayList<>()); // ATTN
            for (String word : words) {
                currentSquare.add(word);
                search(prefixToWords, currentSquare, squares);
                currentSquare.remove(word); // ATTN
            }
        }
    }

    private Map<String, List<String>> getPrefixToWords(String[] words) {
        Map<String, List<String>> prefixToWords = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                String prefix = word.substring(0, i + 1);
                if (!prefixToWords.containsKey(prefix)) {
                    prefixToWords.put(prefix, new ArrayList<>());
                }
                prefixToWords.get(prefix).add(word);
            }
        }
        return prefixToWords;
    }

}
