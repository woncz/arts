/*
 * Copyright [2021]
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

package _059_spiralmatrixii;

import java.util.Arrays;

/**
 * Created by woncz on 3/16/2021.
 */
public class _059 {
    public static void main(String[] args) {
        int n = 3;
        ISolution solution = new Solution2();
        for (int[] i : solution.generateMatrix(5)) {
            System.out.println(Arrays.toString(i));
        }
    }
}

interface ISolution {
    int[][] generateMatrix(int n);
}

class Solution implements ISolution {
    @Override
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];

        int cnt = 0;
        for (int c = 1; c <= n; c += 2) {
            int x = (c - 1) / 2, y = (c - 1) / 2;
            int step = n - c;
            // top
            for (int i = 0; i < n - c + 1; i++) {
                ans[x][y + i] = cnt++;
            }

            // right
            for (int i = 0; i < n - c; i++) {
                ans[x + i + 1][y + n - c] = cnt++;
            }

            // bottom
            for (int i = 0; i < n - c; i++) {
                ans[x + n - c - i][y + n - c] = cnt++;
            }

            // left
            for (int i = 0; i < n - c - 1; i++) {
                ans[x][y + n - c - i] = cnt++;
            }
        }

        return ans;
    }
}

class Solution2 implements ISolution {
    @Override
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int cnt = 1;
        int max = n * n;
        int x = 0, y = 0;
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        int index = 0;
        while (cnt <= max) {
            ans[x][y] = cnt++;
            int nx = x + dx[index], ny = y + dy[index];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || ans[nx][ny] != 0) {
                index = (index + 1) % 4;
            }
            x = x + dx[index];
            y = y + dy[index];
        }
        return ans;
    }
}

class Solution3 implements ISolution {
    @Override
    public int[][] generateMatrix(int n) {
        return new int[0][];
    }
}