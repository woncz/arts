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

package _130_surroundedregions;

import lib.UnionFind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by woncz on 8/11/2020.
 */
public class _130 {
    public static void main(String[] args) {
        char[][] board = new char[][] {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        ISolution solution = new Solution3();
        solution.solve(board);
    }
}

interface ISolution {
    void solve(char[][] board);
}

class Solution implements ISolution {

    int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
    int X = 0, Y = 0;

    @Override
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        X = board.length;
        Y = board[0].length;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (board[i][j] == 'O' && !isMargin(i, j)) {
                    boolean flag = _dfs(i, j, board);
                    if (!flag) board[i][j] = 'X';
                }
            }
        }
        System.out.println(board);
    }


    /**
     * 通过深度优先能否找到在边上的O
     *
     * @return
     */
    boolean _dfs(int x, int y, char[][] board) {
        Stack<Integer> stack = new Stack<>();
        stack.push(x * Y + y);
        Set<Integer> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            int d = stack.pop();
            if (visited.contains(d)) continue;
            visited.add(d);

            int xx = d / Y;
            int yy = d % Y;
            for (int i = 0; i < 4; i++) {
                int nx = xx + dx[i];
                int ny = yy + dy[i];
                if (valid(nx, ny) && board[nx][ny] == 'O') {
                    if (isMargin(nx, ny)) {
                        return true;
                    }
                    stack.push(nx * Y + ny);
                }
            }
        }
        return false;
    }

    boolean isMargin(int x, int y) {
        return x == 0 || x == X - 1 || y == 0 || y == Y - 1;
    }

    boolean valid(int x, int y) {
        return x >= 0 && x < X && y >= 0 && y < Y;
    }
}

class Solution2 implements ISolution {

    int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
    int X = 0, Y = 0;

    @Override
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        X = board.length;
        Y = board[0].length;

        // four sides
        for (int i = 0; i < X; i++) {
            _dfs(i, 0, board);
            _dfs(i, Y - 1, board);
        }
        for (int i = 0; i < Y; i++) {
            _dfs(0, i, board);
            _dfs(X - 1, i, board);
        }

        // recover
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
        System.out.println(Arrays.toString(board));
    }

    void _dfs(int x, int y, char[][] board) {
        if (!valid(x, y) || board[x][y] != 'O') return;

        board[x][y] = 'A';

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            _dfs(nx, ny, board);
        }
    }

    boolean valid(int x, int y) {
        return x >= 0 && x < X && y >= 0 && y < Y;
    }
}

class Solution3 implements ISolution {
    int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
    int X = 0, Y = 0;

    @Override
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        X = board.length;
        Y = board[0].length;

        UnionFind uf = new UnionFind(X * Y + 1);

        // four sides
        for (int i = 0; i < X; i++) {
            _dfs(i, 0, board, uf);
            _dfs(i, Y - 1, board, uf);
        }
        for (int i = 0; i < Y; i++) {
            _dfs(0, i, board, uf);
            _dfs(X - 1, i, board, uf);
        }

        // check
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (board[i][j] == 'O') {
                    if (!uf.connected( X * Y, i * Y + j)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        System.out.println(Arrays.toString(board));
    }

    void _dfs(int x, int y, char[][] board, UnionFind uf) {
        if (!valid(x, y) || board[x][y] != 'O') return;

        if (uf.connected( X * Y, x * Y + y)) return;

        uf.union(X * Y, x * Y + y);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            _dfs(nx, ny, board, uf);
        }
    }

    boolean valid(int x, int y) {
        return x >= 0 && x < X && y >= 0 && y < Y;
    }

    static class UnionFind {

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

}


