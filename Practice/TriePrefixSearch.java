/**
 * Practice Implementation: 26-Way Alphabet Trie for Fast Prefix Autocomplete
 * Revision: #137 | Date: 2026-09-22 14:05:34
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class TriePrefixSearch {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
    }
    private TrieNode root = new TrieNode();
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) curr.children[idx] = new TrieNode();
            curr = curr.children[idx];
        }
        curr.isEndOfWord = true;
    }
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return true;
    }
    public static void main(String[] args) {
        TriePrefixSearch trie = new TriePrefixSearch();
        trie.insert("algorithm");
        System.out.println("Starts with algo: " + trie.startsWith("algo"));
    }
}
