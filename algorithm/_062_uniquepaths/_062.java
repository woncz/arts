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

