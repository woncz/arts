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

package _684_redundantconnection;

import java.util.Arrays;

/**
 * Created by woncz on 1/13/2021.
 */
public class _684 {
    public static void main(String[] args) {
        int[][] edges = new int[][] {{1, 2}, {1, 3}, {2, 3}};
        ISolution solution = new Solution();
        int[] ans = solution.findRedundantConnection(edges);
        System.out.println(Arrays.toString(ans));

        edges = new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        ans = solution.findRedundantConnection(edges);
        System.out.println(Arrays.toString(ans));
    }
}

interface ISolution {
    int[] findRedundantConnection(int[][] edges);
}

class Solution implements ISolution {
    @Override
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1);
        for (int[] edge : edges) {
            int p = edge[0], q = edge[1];
            if (uf.connected(p, q)) {
                 return edge;
            } else {
                uf.union(p, q);
            }
        }
        return new int[0];
    }
}

class UnionFind {

    private int[] parent;
    private int[] size;
    private int count;

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 在p和q之间添加一条连接
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    /**
     * p所在的分量的标识符(0到N-1)
     * @param p
     * @return
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + "is not between 0 and " + (n-1));
        }
    }

    /**
     * 如果p和q存在于同一个分量中则返回true
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连通分量的数量
     * @return
     */
    public int count() {
        return count;
    }
}
