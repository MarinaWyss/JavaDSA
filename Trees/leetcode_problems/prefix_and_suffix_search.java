// https://leetcode.com/problems/prefix-and-suffix-search/description/

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class WordFilter {

    class TrieNode {
        public Map<Character, TrieNode> children;
        public Set<String> words;
        public TrieNode() {
            children = new HashMap<>();
            words = new HashSet<>();
        }
    }

    private TrieNode rootPref;
    private TrieNode rootSuff;
    private Map<String, Integer> indices;

    public WordFilter(String[] words) {
        rootPref = new TrieNode();
        rootSuff = new TrieNode();
        indices = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            insert(words[i]);
            indices.put(words[i], i);
        }
    }

    private void insert(String word) {
        TrieNode prefNode = rootPref;
        TrieNode suffNode = rootSuff;

        // Insert characters as prefixes
        for (char c : word.toCharArray()) {
            if (!prefNode.children.containsKey(c)) {
                prefNode.children.put(c, new TrieNode());
            }
            prefNode = prefNode.children.get(c);
            prefNode.words.add(word);
        }

        // Insert characters as suffixes
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (!suffNode.children.containsKey(c)) {
                suffNode.children.put(c, new TrieNode());
            }
            suffNode = suffNode.children.get(c);
            suffNode.words.add(word);
        }
    }
    
    public int f(String pref, String suff) {
        TrieNode prefNode = rootPref;
        TrieNode suffNode = rootSuff;

        // Get all words with prefix
        for (char c : pref.toCharArray()) {
            if (!prefNode.children.containsKey(c)) {
                return -1;
            }
            prefNode = prefNode.children.get(c);
        }
        Set<String> prefixes = prefNode.words;

        // Get all words with suffix
        for (int i = suff.length() - 1; i >= 0; i--) {
            char c = suff.charAt(i);
            if (!suffNode.children.containsKey(c)) {
                return -1;
            }
            suffNode = suffNode.children.get(c);
        }
        Set<String> suffixes = suffNode.words;

        int index = -1;
        for (String word : prefixes) {
            if (suffixes.contains(word)) {
                index = Math.max(index, indices.get(word));
            }
        }
        return index;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */