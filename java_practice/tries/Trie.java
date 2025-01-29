package java_practice.tries;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean isEow = false;

    public boolean continasKey(char ch) {
        return children.containsKey(ch);
    }

    public TrieNode get(char ch) {
        return children.get(ch);
    }

    public void put(char ch, TrieNode node) {
        children.put(ch, node);
    }

    public void setEow() {
        isEow = true;
    }

    public boolean isEow() {
        return isEow;
    }

}

public class Trie {
    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.continasKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEow();
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEow();

    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.continasKey(ch)) {
                return null;
            }
            node = node.get(ch);
        }
        return node;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));   
        System.out.println(trie.search("appl"));   
        System.out.println(trie.search("ban"));   
        System.out.println(trie.search("bat"));   
        System.out.println(trie.search("banana"));   

    }
}
