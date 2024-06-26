// https://leetcode.com/problems/design-add-and-search-words-data-structure/description/

import java.util.HashMap;
import java.util.Map;

class WordDictionary {

    public class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean end;
    }

    Node root;

    public WordDictionary() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node curr = this.root;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Node());
            }
            curr = curr.children.get(c);
        }
        curr.end = true;
    }
    
    public boolean search(String word) {
        return searchInNode(word, 0, root);
    }

    private boolean searchInNode(String word, int index, Node node) {
        if (index == word.length()) {
            return node.end;
        }
        
        char c = word.charAt(index);
        
        if (c == '.') {
            // Iterate over all children of the current node
            for (char child : node.children.keySet()) {
                // Recursively search the remaining part of the word in each child node
                if (searchInNode(word, index + 1, node.children.get(child))) {
                    // If any child node returns true, return true
                    return true;
                }
            }
            return false;
    
        } else {
            if (!node.children.containsKey(c)) {
                return false;
            }
            // If it exists, recursively search the next character in the corresponding child node
            return searchInNode(word, index + 1, node.children.get(c));
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */