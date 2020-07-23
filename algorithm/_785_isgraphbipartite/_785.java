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

package _785_isgraphbipartite;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by woncz on 7/16/2020.
 */
public class _785 {
    public static void main(String[] args) {
        int[][] graph = new int[][] {new int[] {1, 3}, new int[] {0, 2}, new int[] {1, 3}, new int[] {0, 2}};
        ISolution solution = new Solution1();
        boolean ret = solution.isBipartite(graph);
        System.out.println(ret);

        graph = new int[][] {new int[] {1,2,3}, new int[] {0,2}, new int[] {0,1,3}, new int[] {0,2}};
        solution = new Solution1();
        ret = solution.isBipartite(graph);
        System.out.println(ret);

        graph = new int[][] {new int[] {1},new int[]{0,3},new int[]{3}, new int[]{1,2}};
        solution = new Solution1();
        ret = solution.isBipartite(graph);
        System.out.println(ret);

        graph = new int[][] {{3},{2,4},{1},{0,4},{1,3}};
        solution = new Solution1();
        ret = solution.isBipartite(graph);
        System.out.println(ret);
    }
}

interface ISolution {
    boolean isBipartite(int[][] graph);
}

class Solution implements ISolution {
    int[] X = new int[101];
    int[] Y = new int[101];

    @Override
    public boolean isBipartite(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                int n1 = i;
                int n2 = graph[i][j];
                if (X[n1] > 0 && X[n2] > 0) return false;
                if (Y[n1] > 0 && Y[n2] > 0) return false;
                int[] s1 = this.contains(n1);
                int[] s2 = this.contains(n2);
                if (s1 == null && s2 == null) {
                    X[n1] += 1;
                    Y[n2] += 1;
                } else {
                    if (X[n1] > 0 || Y[n2] > 0) {
                        X[n1] += 1;
                        Y[n2] += 1;
                    }
                    if (Y[n1] > 0 || X[n2] > 0) {
                        Y[n1] += 1;
                        X[n2] += 1;
                    }
                }
            }
        }
        return true;
    }

    int[] contains(int n) {
        if (X[n] > 0) return X;
        if (Y[n] > 0) return Y;
        return null;
    }
}

class Solution1 implements ISolution {

    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] root;

    @Override
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        root = new int[n];
        Arrays.fill(root, UNCOLORED);

        for (int i = 0; i < n; i++) {
            if (root[i] == UNCOLORED) {
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                root[i] = RED;
                while (!q.isEmpty()) {
                    int node = q.poll();
                    int color = root[node] == RED ? GREEN : RED;
                    for (int neighbor : graph[node]) {
                        if (root[neighbor] == UNCOLORED) {
                            q.offer(neighbor);
                            root[neighbor] = color;
                        } else if (root[neighbor] != color) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}

class Solution2 implements ISolution {

    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] root;

    private boolean valid;

    @Override
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        root = new int[n];
        valid = true;
        Arrays.fill(root, UNCOLORED);
        for (int i = 0; i < n && valid; i++) {
            if (root[i] == UNCOLORED) {
                _dfs(i, RED, graph);
            }
        }
        return valid;
    }

    void _dfs(int node, int color, int[][] graph) {
        root[node] = color;
        int nc = color == RED ? GREEN : RED;
        for (int neighbour : graph[node]) {
            if (root[neighbour] == UNCOLORED) {
                _dfs(neighbour, nc, graph);
                if (!valid) {
                    return;
                }
            } else if (root[neighbour] != nc) {
                valid = false;
                return;
            }
        }
    }
}