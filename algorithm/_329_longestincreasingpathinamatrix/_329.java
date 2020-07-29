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

package _329_longestincreasingpathinamatrix;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by woncz on 7/27/2020.
 */
public class _329 {
    public static void main(String[] args) {
        int matrix[][] = new int[][] {{9,9,4},{6,6,8},{2,1,1}};
        ISolution solution = new Solution();
        int m = solution.longestIncreasingPath(matrix);
        System.out.println(m);

        matrix = new int[][]{{3,4,5},{3,2,6},{2,2,1}};
        solution = new Solution();
        m = solution.longestIncreasingPath(matrix);
        System.out.println(m);

        matrix = new int[][]{{7,8,9},{9,7,6},{7,2,3}};
        solution = new Solution();
        m = solution.longestIncreasingPath(matrix);
        System.out.println(m);

        matrix = new int[][] {{0,1,2,3,4,5,6,7,8,9},{19,18,17,16,15,14,13,12,11,10},{20,21,22,23,24,25,26,27,28,29},{39,38,37,36,35,34,33,32,31,30},{40,41,42,43,44,45,46,47,48,49},{59,58,57,56,55,54,53,52,51,50},{60,61,62,63,64,65,66,67,68,69},{79,78,77,76,75,74,73,72,71,70},{80,81,82,83,84,85,86,87,88,89},{99,98,97,96,95,94,93,92,91,90},{100,101,102,103,104,105,106,107,108,109},{119,118,117,116,115,114,113,112,111,110},{120,121,122,123,124,125,126,127,128,129},{139,138,137,136,135,134,133,132,131,130},{0,0,0,0,0,0,0,0,0,0}};
        solution = new Solution();
        m = solution.longestIncreasingPath(matrix);
        System.out.println(m);
    }
}
interface ISolution {
    int longestIncreasingPath(int[][] matrix);
}

class Solution implements ISolution {

    int[] X = new int[] {-1, 0, 0, 1};
    int[] Y = new int[] {0, -1, 1, 0};
    int M, N;
    int[][] matrix;
    int[][] dp;

    @Override
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null|| matrix.length == 0 || matrix[0].length == 0) return 0;
        M = matrix.length;
        N = matrix[0].length;
        this.matrix = matrix;
        this.dp = new int[M][N];

        int ans = 0;
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                visited.clear();
                dp[i][j] = _process(i, j, visited, 1);
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans;
    }

    int _process(int x, int y, Set<String> visited, int len) {
        if (dp[x][y] != 0) return dp[x][y];
        dp[x][y]++;
        for (int i = 0; i < 4; i++) {
            int nx = X[i] + x;
            int ny = Y[i] + y;
            if (valid(nx, ny) && !visited.contains(nx + "-" + ny) && matrix[x][y] < matrix[nx][ny]) {
                visited.add(nx + "-" + ny);
                dp[x][y] = Math.max(dp[x][y], _process(nx, ny, visited, len + 1) + 1);
                // 状态回溯
                visited.remove(nx + "-" + ny);
            }
        }
        return dp[x][y];
    }

    boolean valid(int nx, int ny) {
        if (nx < 0 || nx >= M) return false;
        if (ny < 0 || ny >= N) return false;
        return true;
    }
}

/**
 * 带有回溯
 */
class SolutionDFS implements ISolution {

    int[] X = new int[] {-1, 0, 0, 1};
    int[] Y = new int[] {0, -1, 1, 0};
    int M, N;
    int[][] matrix;
    int[][] dp;

    @Override
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null|| matrix.length == 0 || matrix[0].length == 0) return 0;
        M = matrix.length;
        N = matrix[0].length;
        this.matrix = matrix;
        this.dp = new int[M][N];

        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = _dfs(i, j);
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans;
    }

    int _dfs(int x, int y) {
        // cache
        if (dp[x][y] != 0) return dp[x][y];
        dp[x][y]++;

        for (int i = 0; i < 4; i++) {
            int nx = X[i] + x;
            int ny = Y[i] + y;
            if (valid(nx, ny) && matrix[x][y] < matrix[nx][ny]) {
                // 此时dp[x][y]具备自更新功能
                dp[x][y] = Math.max(dp[x][y], _dfs(nx, ny) + 1);
            }
        }
        return dp[x][y];
    }

    boolean valid(int nx, int ny) {
        if (nx < 0 || nx >= M) return false;
        if (ny < 0 || ny >= N) return false;
        return true;
    }
}
