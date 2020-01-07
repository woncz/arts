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

package algo4.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Board {

    private int[][] tiles;

    private int N;

    // cache priority
    private int hamming = -1;

    private int manhattan = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null || tiles.length != tiles[0].length) throw new IllegalArgumentException();
        this.N = tiles.length;
        if (this.N < 2 || this.N > 128) throw new IllegalArgumentException();
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            this.tiles[i] = Arrays.copyOf(tiles[i], N);
        }
    }

    // string representation of this board
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            sb.append(" ");
            for (int j = 0; j < N; j++) {
                sb.append(tiles[i][j]).append("  ");
            }
            if (i != N - 1)
                sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return N;
    }

    // number of tiles out of place
    public int hamming() {
        if (hamming >= 0) return hamming;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != (i * N + j + 1)) {
                    cnt++;
                }
            }
        }
        hamming = cnt;
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        if (manhattan >= 0) return manhattan;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0) {
                    int x = (tiles[i][j] - 1) / N;
                    int y = (tiles[i][j] - 1) % N;
                    cnt += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }
        manhattan = cnt;
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;

        Board that = (Board) y;
        if (this.N != that.N) return false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    // max 4
                    int[] dx = new int[] {0, 0, -1, 1};
                    int[] dy = new int[] {-1, 1, 0, 0};

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        // valid position
                        if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                            // clone data
                            int[][] t = new int[N][];
                            for (int m = 0; m < N; m++) {
                                t[m] = Arrays.copyOf(tiles[m], N);
                            }

                            // move 1 step
                            t[i][j] = t[nx][ny];
                            t[nx][ny] = 0;

                            Board nb = new Board(t);
                            boards.add(nb);
                        }
                    }
                }
            }
        }
        return boards;
    }
    private void exch(int[][] a, int ai, int aj, int bi, int bj) {
        int t = a[ai][aj];
        a[ai][aj] = a[bi][bj];
        a[bi][bj] = t;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] t = new int[N][];
        for (int i = 0; i < N; i++) {
            t[i] = Arrays.copyOf(tiles[i], N);
        }

        // just use a special case
        if (t[0][0] != 0 && t[0][1] != 0) {
            int tmp = t[0][0];
            t[0][0] = t[0][1];
            t[0][1] = tmp;
        } else {
            int tmp = t[N - 1][0];
            t[N - 1][0] = t[N - 1][1];
            t[N - 1][1] = tmp;
        }
        return new Board(t);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = new int[][]{
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        Board board = new Board(tiles);
        System.out.println(board);

        System.out.println("dimension : " + board.dimension());

        System.out.println("hamming : " + board.hamming());
        System.out.println("manhattan : " + board.manhattan());

        Iterator<Board> it = board.neighbors().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println(board.twin());

        System.out.println(board.isGoal());

        tiles = new int[][] {
                {1,  2,  3 },
                {5,  7,  6 },
                {0,  4,  8}
        };

        board = new Board(tiles);
        it = board.neighbors().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}