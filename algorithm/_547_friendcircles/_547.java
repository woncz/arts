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

package _547_friendcircles;

public class _547 {
    public static void main(String[] args) {
        int[][] M = new int[][] {{1,1,0},{1,1,0},{0,0,1}};
        ISolution s1 = new Solution1();
        int cnt = s1.findCircleNum(M);
        System.out.println(cnt);

        M = new int[][] {{1,1,0}, {1,1,1}, {0,1,1}};
        s1 = new Solution1();
        cnt = s1.findCircleNum(M);
        System.out.println(cnt);

        M = new int[][] {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        s1 = new Solution1();
        cnt = s1.findCircleNum(M);
        System.out.println(cnt);
    }
}

interface ISolution {
    int findCircleNum(int[][] M);
}

class Solution1 implements ISolution {
    @Override
    public int findCircleNum(int[][] M) {
        if (M == null || M[0] == null) return 0;

        final int X = M.length;
        final int Y = M[0].length;
        UnionFind uf = new UnionFind(X * Y, M);

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        for (int x = 0; x < X; x++ ) {
            for (int y = 0; y <= x; y++) {
                if (M[x][y] == 1) {
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (isValid(M, nx, ny)) {
                            uf.union(x * X + y, nx * X + ny);
                        }
                    }
                }
            }
        }

        return uf.count();
    }

    boolean isValid(int[][] M, int x, int y) {
        return (x >= 0 && x < M.length && y >= 0 && y < M[0].length && M[x][y] == 1);
    }
}

/**
 * DFS
 */
class Solution2 implements ISolution {
    @Override
    public int findCircleNum(int[][] M) {
        // TODO
        return 0;
    }
}

/**
 * BFS
 */
class Solution3 implements ISolution {
    @Override
    public int findCircleNum(int[][] M) {
        // TODO
        return 0;
    }
}


class UnionFind {

    private int[] parent;
    private int[] size;
    private int count;

    public UnionFind(int n, int[][] M) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            if (M[i / M.length][i % M[0].length] == 1) {
                parent[i] = i;
                size[i] = 1;
            } else {
                count--;
            }
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