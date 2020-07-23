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

package _064_minimumpathsum;

/**
 * Created by woncz on 7/23/2020.
 */
public class _064 {
    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,3,1}, {1,5,1},{4,2,1}};
        ISolution solution = new Solution();
        System.out.println(solution.minPathSum(grid));
    }
}

interface ISolution {
    int minPathSum(int[][] grid);
}

/**
 * 动态规划，dp[current] = f(dp[left], dp[up])，状态转移由左边或者上边演化而来
 * 可以采用状态压缩（路径压缩、滚动数组）技术，缩减空间复杂度
 */
class Solution implements ISolution {
    @Override
    public int minPathSum(int[][] grid) {
        if (grid == null || grid[0] == null) return 0;
        int N = grid[0].length;
        int[] dp = new int[N];

        dp[0] = grid[0][0];
        for (int i = 1; i < N; i++) {
            dp[i] += dp[i - 1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (j == 0) {
                    dp[j] += grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }

        return dp[N - 1];
    }
}