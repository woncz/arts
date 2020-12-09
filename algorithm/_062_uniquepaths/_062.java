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

package _062_uniquepaths;

public class _062 {
    public static void main(String[] args) {
        int m = 3, n = 2;
        ISolution solution = new Solution2();
        System.out.println(solution.uniquePaths(m, n));

        m = 3; n = 7;
        System.out.println(solution.uniquePaths(m, n));
    }
}

interface ISolution {
    int uniquePaths(int m, int n);
}

class Solution implements ISolution {
    @Override
    public int uniquePaths(int m, int n) {
        int[][] state = new int[m][n];

        for (int i = 0; i < m; i++) {
            state[i][n -1] = 1;
        }
        for (int i = 0; i < n; i++) {
            state[m - 1][i] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                state[i][j] = state[i][j + 1] + state[i + 1][j];
            }
        }

        return state[0][0];
    }
}

class Solution2 implements ISolution {
    @Override
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }
}

