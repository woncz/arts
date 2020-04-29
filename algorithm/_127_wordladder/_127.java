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

package _127_wordladder;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _127 {
    public static void main(String[] args) {

    }
}

interface ISolution {
    int ladderLength(String beginWord, String endWord, List<String> wordList);
}

/**
 * DFS
 */
class Solution1 implements ISolution {
    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> alternative = new HashSet<>(wordList);
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        int step = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                for (int j = 0; j < current.length(); j++) {
                    StringBuilder sb = new StringBuilder(current);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(j, c);
                        String newWord = sb.toString();
                        if (alternative.contains(newWord)) {
                            if (newWord.equals(endWord)) return step + 1;
                            alternative.remove(newWord);
                            queue.offer(newWord);
                        }
                    }
                }
            }
            step++;
        }

        return 0;

    }
}

/**
 * BFS
 */
class Solution2 implements ISolution {
    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> alternative = new HashSet<>(wordList);
        int step = 1;
        Queue<String> queue = new ArrayDeque();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { // every word
                String current = queue.poll();
                for (int j = 0; j < current.length(); j++) { // every charactor
                    StringBuilder sb = new StringBuilder(current);
                    for (char k = 'a'; k <= 'z'; k++) { // 26 possibilities
                        sb.setCharAt(j, k);
                        String newWord = sb.toString();
                        if (alternative.contains(newWord)) {
                            if (newWord.equals(endWord)) return step + 1;
                            alternative.remove(newWord);
                            queue.offer(newWord);
                        }
                    }
                }
            }
            step++;
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

        ISolution s = new Solution2();
        int step = s.ladderLength(beginWord, endWord, wordList);
        System.out.println(step);

        ISolution s1 = new Solution1();
        step = s1.ladderLength(beginWord, endWord, wordList);
        System.out.println(step);

    }
}
