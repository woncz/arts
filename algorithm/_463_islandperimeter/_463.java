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

package _463_islandperimeter;

/**
 * Created by woncz on 10/30/2020.
 */
public class _463 {
    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };

        ISolution solution = new Solution2();
        int ans = solution.islandPerimeter(grid);
        System.out.println(ans);
    }
}

interface ISolution {
    int islandPerimeter(int[][] grid);
}

class Solution implements ISolution {

    int X, Y;

    @Override
    public int islandPerimeter(int[][] grid) {
        // 1. 计算所有陆地
        // 2. 计算所有相连
        // 3. 周长 = 4 * [1] - [2]

        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;

        X = grid.length; Y = grid[0].length;

        int lands = 0, connect = 0;

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (grid[i][j] == 1) {
                    lands++;
                    connect += connect(grid, i, j);
                }
            }
        }

        return 4 * lands - connect;
    }

    int connect(int[][] grid, int x, int y) {
        int cnt = 0;
        if (valid(x - 1, y) && grid[x - 1][y] == 1) cnt++;
        if (valid(x + 1, y) && grid[x + 1][y] == 1) cnt++;
        if (valid(x, y - 1) && grid[x][y - 1] == 1) cnt++;
        if (valid(x, y + 1) && grid[x][y + 1] == 1) cnt++;
        return cnt;
    }

    boolean valid(int x, int y) {
        if (x < 0 || x >= X) return false;
        if (y < 0 || y >= Y) return false;
        return true;
    }
}

class Solution2 implements ISolution {

    int[] dx = new int[] {0, 0, -1, 1};
    int[] dy = new int[] {1, -1, 0, 0};

    @Override
    public int islandPerimeter(int[][] grid) {
        int X = grid.length, Y = grid[0].length;
        int ans = 0;
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                if (grid[x][y] == 1) {
                    int cnt = 0;
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i], ny = y + dy[i];
                        if (nx < 0 || nx >= X || ny < 0 || ny >= Y || grid[nx][ny] == 0) {
                            cnt++;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }
}