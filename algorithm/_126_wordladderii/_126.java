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

package _126_wordladderii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class _126 {
    public static void main(String[] args) {

    }
}

interface ISolution {
    List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList);
}

/**
 * BFS
 */
class Solution1 implements ISolution {
    @Override
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        return null;
    }
}

/**
 * BFS + DFS + Backtrack
 */
class Solution2 implements ISolution {

    @Override
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> alternative = new HashSet<>(wordList);
        bfs(beginWord, endWord, graph, alternative);

        List<String> path = new LinkedList<>();
        List<List<String>> ans = new LinkedList<>();
        dfs(beginWord, endWord, graph, path, ans);

        return ans;
    }

    private void bfs(String current, String target, Map<String, List<String>> graph, Set<String> alternative) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> toVisit = new HashSet<>();
        queue.offer(current);
        toVisit.add(current);
        boolean eureka = false;

        while (!queue.isEmpty()) {
            visited.addAll(toVisit);
            toVisit.clear();
            int cnt = queue.size();

            for (int i = 0; i < cnt; i++) {
                String word = queue.poll();
                List<String> children = getNextLevel(word, alternative);
                for (String child : children) {
                    if (child.equals(target)) {
                        eureka = true;
                    }
                    if (!visited.contains(child)) {
                        if (!graph.containsKey(word)) {
                            graph.put(word, new ArrayList<>());
                        }
                        graph.get(word).add(child);
                    }
                    if (!visited.contains(child) && !toVisit.contains(child)) {
                        queue.offer(child);
                        toVisit.add(child);
                    }
                }
            }

            if (eureka) break; // BFS找到一个即返回，最终的结果由DFS提供，BFS找到就是最短路径
        }

    }

    private void dfs(String current, String target, Map<String, List<String>> graph, List<String> path, List<List<String>> ans) {
        path.add(current);

        if (current.equals(target)) {
            ans.add(new ArrayList<>(path));
        } else if (graph.containsKey(current)) {
            for (String child : graph.get(current)) { // related nodes
                dfs(child, target, graph, path, ans);
            }
        }

        // revert state, backtracking
        path.remove(path.size() - 1);
    }

    // find children without itself
    private List<String> getNextLevel(String current, Set<String> alternative) {
        List<String> l = new ArrayList<>();
        for (int i = 0; i < current.length(); i++) {
            StringBuilder sb = new StringBuilder(current);
            for (char c = 'a'; c <= 'z'; c++) {
                sb.setCharAt(i, c);
                String next = sb.toString();
                if (!current.equals(next) && alternative.contains(next)) {
                    l.add(next);
                }
            }
        }
        return l;
    }


    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        ISolution s2 = new Solution2();
        List<List<String>> lists = s2.findLadders(beginWord, endWord, wordList);
        System.out.println(lists);
    }
}

class Solution3 implements ISolution {
    @Override
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> path = new ArrayList<>();
        List<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, List<String>> graph = new HashMap<String, List<String>>();
        HashSet<String> dict = new HashSet<>(wordList);
        buildGraph(beginWord, endWord, graph, dict);
        dfs(beginWord, endWord, graph, path, result);
        return result;
    }

    private void buildGraph(String beginWord, String endWord, HashMap<String, List<String>> graph, HashSet<String> dict) {
        HashSet<String> visited = new HashSet<>();
        HashSet<String> toVisit = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        toVisit.add(beginWord);
        boolean foundEnd = false;

        while (!queue.isEmpty()) {
            visited.addAll(toVisit);
            toVisit.clear();
            int count = queue.size();

            for (int i = 0; i < count; i++) {
                String word = queue.poll();
                List<String> children = getNextLevel(word, dict);
                for (String child : children) {
                    if (child.equals(endWord)) {
                        foundEnd = true;
                    }
                    if (!visited.contains(child)) {
                        if (!graph.containsKey(word)) {
                            graph.put(word, new ArrayList<String>());
                        }
                        graph.get(word).add(child);
                    }
                    if (!visited.contains(child) && !toVisit.contains(child)) {
                        queue.offer(child);
                        toVisit.add(child);
                    }
                }
            }

            if (foundEnd) break;
        }
    }

    private List<String> getNextLevel(String word, HashSet<String> dict) {
        List<String> result = new ArrayList<>();
        char[] chs = word.toCharArray();

        for (int i = 0; i < chs.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (chs[i] == c) {
                    continue;
                }
                char t = chs[i];
                chs[i] = c;
                String target = String.valueOf(chs);
                if (dict.contains(target)) {
                    result.add(target);
                }
                chs[i] = t;
            }
        }

        return result;
    }

    private void dfs(String curWord, String endWord, HashMap<String, List<String>> graph, List<String> path, List<List<String>> result) {
        path.add(curWord);

        if (curWord.equals(endWord)) {
            result.add(new ArrayList<String>(path));
        }
        else if (graph.containsKey(curWord)) {
            for (String nextWord : graph.get(curWord)) {
                dfs(nextWord, endWord, graph, path, result);
            }
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        ISolution s = new Solution3();
        List<List<String>> lists = s.findLadders(beginWord, endWord, wordList);
        System.out.println(lists);
    }
}