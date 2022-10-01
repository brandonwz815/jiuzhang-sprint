"""
https://www.lintcode.com/problem/634
"""


class Solution:
    def word_squares(self, words):
        """Get the word squares"""
        prefix_to_words = self.get_prefix_to_words(words)

        squares = []
        for word in words:
            self.search(prefix_to_words, [word], squares)
        return squares

    def search(self, prefix_to_words, square, squares):
        """Recursive"""
        current_index = len(square)
        if current_index == len(squares):
            squares.append(list(square))  # ATTN 'list'
        else:
            # ATTN Pruning
            # Use two loops for this 2D matrix
            # The outer explicit for loop iterates over rows
            # the inner implicit loop iterates over columns
            for row_index in range(current_index, len(squares)):
                prefix = "".join([square[i][row_index] for i in range(current_index)])
                if prefix not in prefix_to_words:
                    return

            prefix = "".join([square[i][current_index] for i in range(current_index)])
            for word in prefix_to_words.get(prefix, []):
                square.append(word)
                self.search(prefix_to_words, square, squares)
                square.pop()  # ATTN

    def get_prefix_to_words(self, words):
        """Util to prepare prefix_to_words"""
        prefix_to_words = {}

        for word in words:
            for i in range(len(word)):
                prefix = word[: i + 1]
                if prefix not in prefix_to_words:
                    prefix_to_words[prefix] = []
                prefix_to_words[prefix].append(word)

        return prefix_to_words


# Don't forget to create a new list out of the current_square!!
