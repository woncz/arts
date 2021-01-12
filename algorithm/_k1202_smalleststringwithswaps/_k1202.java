/*
 * Copyright [2020]
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

package _k1202_smalleststringwithswaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by woncz on 1/11/2021.
 */
public class _k1202 {
    public static void main(String[] args) {
        ISolution solution = new Solution();
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(1, 2));
        System.out.println(solution.smallestStringWithSwaps(s, pairs));
    }
}

interface ISolution {
    String smallestStringWithSwaps(String s, List<List<Integer>> pairs);
}

class Solution implements ISolution {
    @Override
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() == 0) return null;

        UnionFind uf = new UnionFind(s.length());
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> cache = new HashMap<>();
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            int root = uf.find(i);
            cache.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(ss[i]);
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            ans.append(cache.get(uf.find(i)).poll());
        }
        return ans.toString();
    }
}

class UnionFind {
    private int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (rootP < rootQ) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
        }
    }

    public int find(int p) {
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}