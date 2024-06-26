// https://leetcode.com/problems/word-search-ii/description/

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        // Use a Trie to efficiently search the words
        Trie trie = buildTrie(words);
        // Use a set to store de-duped words found
        Set<String> wordsFound = new HashSet<>();

        // Iterate over each cell on the board and perform DFS starting from each cell
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, trie, wordsFound, i, j);
            }
        }
        // Convert the set to an ArrayList
        return new ArrayList<>(wordsFound);
    }

    public void dfs(char[][] board, Trie node, Set<String> wordsFound, int i, int j) {
        // Check if current position is out of bounds 
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
            // or if a cell is already visited (marked by #)
            board[i][j] == '#' ||
            // or character doesn't exist in the Trie
            /* Note: When we subtract the ASCII value of 'a' 
             from the ASCII value of board[i][j], we get an 
             index in the range 0-25, corresponding to the position 
             of the character in the alphabet. So, node.next[board[i][j] - 'a'] == null 
             checks whether there is a Trie node corresponding 
             to the current character board[i][j] in the Trie.*/
            node.next[board[i][j] - 'a'] == null) {
                return;
            }
        
        // If the current Trie node contains a complete word, add word to the result set.
        if (node.next[board[i][j] - 'a'].word != null) {
            wordsFound.add(node.next[board[i][j] - 'a'].word);
        }

        // Move to the next Trie node
        node = node.next[board[i][j] - 'a'];
        char c = board[i][j];
        // Mark cell as visited
        board[i][j] = '#';

        // Recursively call DFS on the four neighboring cells
        dfs(board, node, wordsFound, i - 1, j);
        dfs(board, node, wordsFound, i + 1, j);
        dfs(board, node, wordsFound, i , j - 1);
        dfs(board, node, wordsFound, i, j + 1);

        // Restore the cell's original char afterwards
        board[i][j] = c;
    }    

    public Trie buildTrie(String[] words) {
        Trie root = new Trie();

        for (String w : words) {
            Trie p = root;
            for (char c : w.toCharArray()) {
                // Get index of next.c (node in array) and check if it is null
                if (p.next[c - 'a'] == null) {
                    // If it is, create a new node in the array
                    p.next[c - 'a'] = new Trie();
                }
                p = p.next[c - 'a'];  // Move to next node in Trie
            }
            p.word = w; // Store the word as the last node
        }
        return root;
    } 
    private class Trie {
        Trie[] next = new Trie[26];
        String word = null;
    }
}