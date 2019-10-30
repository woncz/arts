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

package _200_numberofislands;

public class Main {
    public static void main(String[] args) {
        char[][] canvas = new char[][]{"11110".toCharArray(), "11010".toCharArray(), "11000".toCharArray(), "00000".toCharArray()};
        ISolution s1 = new Solution1();
        int cnt = s1.numIslands(canvas);
        System.out.println(cnt);

        canvas = new char[][] {"11000".toCharArray(), "11000".toCharArray(), "00100".toCharArray(), "00011".toCharArray()};
        cnt = s1.numIslands(canvas);
        System.out.println(cnt);

        canvas = new char[][]{};
        cnt = s1.numIslands(canvas);
        System.out.println(cnt);

    }
}

interface ISolution {
    int numIslands(char[][] grid);
}

/**
 * DFS 冲水法更形象
 * time complexity : O(X * Y)
 */
class Solution1 implements ISolution {
    int X = Integer.MIN_VALUE;
    int Y = Integer.MIN_VALUE;
    @Override
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        // int data
        X = grid.length;
        Y = grid[0].length;
        int cnt = 0;

        for (int x= 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                if (grid[x][y] == '1') {
                    dfs(grid, x, y);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    /**
     * flush recursively
     *
     * @param grid
     * @param x
     * @param y
     */
    void dfs(char[][] grid, int x, int y) {
        if (x < 0 || x >= X || y >= Y || y < 0 || grid[x][y] != '1') return;
        // current
        grid[x][y] = '0';
        // up
        dfs(grid, x - 1, y);
        // left
        dfs(grid, x, y - 1);
        // right
        dfs(grid, x, y + 1);
        // down
        dfs(grid, x + 1, y);
    }
}

/**
 * BFS
 */
class Solution2 implements ISolution {
    @Override
    public int numIslands(char[][] grid) {
        return 0;
    }
}