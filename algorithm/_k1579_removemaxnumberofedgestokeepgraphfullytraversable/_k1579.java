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

package _k1579_removemaxnumberofedgestokeepgraphfullytraversable;

import java.util.Arrays;

/**
 * Created by woncz on 1/27/2021.
 */
public class _k1579 {
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}};
        ISolution solution = new Solution();
        System.out.println(solution.maxNumEdgesToRemove(n, edges));

        n = 4;
        edges = new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 4}, {2, 1, 4}};
        System.out.println(solution.maxNumEdgesToRemove(n, edges));

        n = 4;
        edges = new int[][]{{3, 2, 3}, {1, 1, 2}, {2, 3, 4}};
        System.out.println(solution.maxNumEdgesToRemove(n, edges));
    }

}

interface ISolution {
    int maxNumEdgesToRemove(int n, int[][] edges);
}

class Solution implements ISolution {

    @Override
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> b[0] - a[0]);
        int edgeAdd = 0;

        UnionFind alice = new UnionFind(n);
        UnionFind bob = new UnionFind(n);

        for (int[] e : edges) {
            int type = e[0];
            int a = e[1];
            int b = e[2];

            switch (type) {
                case 3:
                    if (alice.union(a, b) | bob.union(a, b)) {
                        edgeAdd++;
                    }
                    break;
                case 2:
                    if (bob.union(a, b)) {
                        edgeAdd++;
                    }
                    break;
                case 1:
                    if (alice.union(a, b)) {
                        edgeAdd++;
                    }
                    break;
                default:
                    break;

            }
        }
        return (alice.united() && bob.united()) ? edges.length - edgeAdd : -1;
    }
}

class UnionFind {
    private int[] parent;
    int count;

    public UnionFind(int n) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        count = n;
    }

    public boolean union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return false;

        if (rootP < rootQ) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
        }
        count--;
        return true;
    }

    public int find(int p) {
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean united() {
        return this.count == 1;
    }
}