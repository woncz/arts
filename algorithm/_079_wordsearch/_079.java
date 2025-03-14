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

package _079_wordsearch;

/**
 * Created by woncz on 9/22/2020.
 */
public class _079 {
    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCCED";

        ISolution solution = new Solution();
        System.out.println(solution.exist(board, word));

        word = "SEE";
        System.out.println(solution.exist(board, word));

        word = "ABCB";
        System.out.println(solution.exist(board, word));


        board = new char[][] {{'a'}};
        word = "a";
        System.out.println(solution.exist(board, word));

    }
}

interface ISolution {
    boolean exist(char[][] board, String word);
}

class Solution implements ISolution {

    @Override
    public boolean exist(char[][] board, String word) {
        int X = board.length, Y = board[0].length;
        boolean[][] visited = new boolean[X][Y];

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (this._dfs(board, word, i, j, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean _dfs(char[][] board, String word, int x, int y, int index, boolean[][] visited) {
        // terminator
        if (board[x][y] != word.charAt(index)) {
            return false;
        } else if (index == word.length() - 1) {
            return true;
        }

        // neighbours
        int[][] dd = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        boolean result = false;
        visited[x][y] = true;
        for (int[] d : dd) {
            int nx = x + d[0], ny = y + d[1];
            if (valid(board, nx, ny) && !visited[nx][ny]) {
                boolean ans = this._dfs(board, word, nx, ny, index + 1, visited);
                if (ans) {
                    result = true;
                    break;
                }
            }
        }
        // recover
        visited[x][y] = false;

        return result;
    }

    boolean valid(char[][] board, int x, int y) {
        return 0 <= x && x < board.length && 0 <= y && y < board[0].length;
    }
}
