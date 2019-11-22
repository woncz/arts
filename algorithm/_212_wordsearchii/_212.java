/*
 * Copyright [2017]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package _212_wordsearchii;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _212 {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = new String[] {"oath","pea","eat","rain"};
        ISolution s = new Solution2();
        System.out.println(s.findWords(board, words));

        board = new char[][] {{'a','b'},{'c','d'}};
        words = new String[] {"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};
        s = new Solution2();
        System.out.println(s.findWords(board, words));
    }
}

interface ISolution {
    List<String> findWords(char[][] board, String[] words);
}

/**
 * DFS
 */
class Solution1 implements ISolution {
    @Override
    public List<String> findWords(char[][] board, String[] words) {
        return null;
    }
}

/**
 * Trie
 */
class Solution2 implements ISolution {

    private Trie root = new Trie();

    private boolean[][] visited;

    private Set<String> ans = new HashSet<>();

    int[] xx = new int[] {-1, 1, 0, 0};
    int[] yy = new int[] {0, 0, -1, 1};

    void buildTrie(String[] words) {
        for (String word : words) {
            root.insert(word);
        }
    }

    List<String> search(char[][] board) {
        ans.clear();
        visited = new boolean[board.length][board[0].length];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                dfs(0, board, "", x, y);
            }
        }
        return new ArrayList<>(ans);
    }

    void dfs(int level, char[][] board, String prefix, int x, int y) {
        // terminator
        if (x < 0 || x > board.length - 1 || y < 0 || y > board[0].length - 1 || visited[x][y]) {
            return;
        }
        prefix += board[x][y];
        if (!root.startsWith(prefix)) {
            return;
        }

        // process current logic
        visited[x][y] = true;
        if (root.search(prefix)) {
            ans.add(prefix);
        }

        // drill down
        for (int i = 0; i < 4; i++) {
            int nx = x + xx[i], ny = y + yy[i];
            dfs(level + 1, board, prefix, nx, ny);
        }

        // revert state
        visited[x][y] = false;
    }

    @Override
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null|| board.length == 0 || words == null || words.length == 0) {
            return Collections.emptyList();
        }
        buildTrie(words);
        return search(board);
    }


    /**
     * 字典树、前缀树
     */
    class Trie {

        private TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            char[] letters = word.toCharArray();
            TrieNode node = root;
            for(char c : letters) {
                int index = c - 'a';
                if (node.getChildren()[index] == null) {
                    node.getChildren()[index] = new TrieNode();
                }
                node = node.getChildren()[index];
            }
            node.end();
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] letters = word.toCharArray();
            TrieNode node = root;
            for (char c : letters) {
                int index = c - 'a';
                if (node.getChildren()[index] == null) {
                    return false;
                }
                node = node.getChildren()[index];
            }
            return node.isWord();
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] letters = prefix.toCharArray();
            TrieNode node = root;
            for (char c : letters) {
                int index = c - 'a';
                if (node.getChildren()[index] == null) {
                    return false;
                }
                node = node.getChildren()[index];
            }
            return true;
        }
    }

    class TrieNode {

        public static final int MAX = 26;

        private TrieNode[] children;

        private boolean isWord;

        public TrieNode() {
            children = new TrieNode[MAX];
        }

        public void end() {
            this.isWord = true;
        }

        public boolean isWord() {
            return isWord;
        }

        public TrieNode[] getChildren() {
            return children;
        }
    }
}

/**
 * Most vote
 */
class Solution3 implements ISolution {
    @Override
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j, p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

}
