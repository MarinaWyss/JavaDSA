// https://leetcode.com/problems/implement-trie-prefix-tree/description/

import java.util.HashMap;
import java.util.Map;

public class Trie {
    
    public class TrieNode {
        // Map to store child nodes; each key is a character
        Map<Character, TrieNode> children = new HashMap<>();
        // Boolean to indicate if the node represents the end of a word
        boolean word;
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        // Traverse the trie and create nodes as necessary
        TrieNode curr = getNode(word, true);
        // Mark the end of the word
        curr.word = true;
    }

    public boolean search(String word) {
        // Traverse the trie to find the end node of the word
        TrieNode curr = getNode(word, false);
        // Return true if the word exists, false otherwise
        return curr != null && curr.word;
    }

    public boolean startsWith(String prefix) {
        // Traverse the trie to find the end node of the prefix
        TrieNode curr = getNode(prefix, false);
        // Return true if the prefix exists, false otherwise
        return curr != null;
    }

    // Private method to traverse the trie
    private TrieNode getNode(String str, boolean createIfMissing) {
        // Start from the root node
        TrieNode curr = this.root;
        // Iterate over each character in the string
        for (char c : str.toCharArray()) {
            // If the current character is not a child node
            if (!curr.children.containsKey(c)) {
                // If createIfMissing is true, create a new child node
                if (createIfMissing) {
                    curr.children.put(c, new TrieNode());
                } else {
                    // If createIfMissing is false, return null (indicating the string is not found)
                    return null;
                }
            }
            // Move to the child node corresponding to the current character
            curr = curr.children.get(c);
        }
        // Return the node corresponding to the end of the string
        return curr;
    }
}
