import java.util.HashMap;
import java.util.Map;

// Implementation of a Trie for lowercase alphabet
// Note: There is a lot of duplicated code that could be refactored into a getNode method
// but I am keeping it this way for ease of understanding the logic 
public class Trie {
    
    public class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean word;  // Is leaf
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = this.root;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        // We mark that this is the end of a word
        curr.word = true;
    }

    public boolean search(String word) {
        TrieNode curr = this.root;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                // If we're missing a letter, the word is not there
                return false;
            }
            curr = curr.children.get(c);
        }
        // If we reach the end, return if this is the end of a word or not
        return curr.word;
    }

    // Return true if any words start with this prefix in the Trie
    public boolean startsWith(String prefix) {
        TrieNode curr = this.root;
        for (char c : prefix.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }
}
