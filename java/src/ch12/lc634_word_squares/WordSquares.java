package ch12.lc634_word_squares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSquares {

    public List<List<String>> wordSquares(String[] words) {
        Map<String, List<String>> prefixToWords = getPrefixToWords(words);
        List<List<String>> squares = new ArrayList<>();
        for (String word : words) {
            List<String> currentSquare = new ArrayList<>();
            currentSquare.add(word);
            search(prefixToWords, currentSquare, squares);
        }
        return squares;
    }

    private void search(Map<String, List<String>> prefixToWords, List<String> currentSquare,
            List<List<String>> squares) {
        int currentIndex = currentSquare.size();
        if (currentIndex == squares.size()) {
            squares.add(new ArrayList<>(currentSquare)); // ATTN use new ArrayList<>()
        } else {
            /*
             * ATTN pruning
             * Use two for this 2D matrix
             * The outer loop iterates over columns of currentSquare
             * the inner loop iterates over rows currentSquare
             */
            for (int i = currentIndex; i < squares.size(); i++) { 
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < currentIndex; j++) {
                    sb.append(currentSquare.get(j).charAt(i));
                }
                if (!prefixToWords.containsKey(sb.toString())) {
                    return;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < currentIndex; i++) {
                sb.append(currentSquare.get(i).charAt(currentIndex));
            }
            String prefix = sb.toString();
            List<String> words = prefixToWords.get(prefix);
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
                    prefixToWords.put(prefix, new ArrayList<String>());
                } else {
                    prefixToWords.get(prefix).add(word);
                }
            }
        }
        return prefixToWords;
    }
}
