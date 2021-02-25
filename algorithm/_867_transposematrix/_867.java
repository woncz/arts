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

package _867_transposematrix;

import java.util.Arrays;

/**
 * Created by woncz on 2/25/2021.
 */
public class _867 {
    public static void main(String[] args) {
        ISolution solution = new Solution();
        int[][] matrix = {{1,2,3}, {4, 5, 6}};
        int[][] ret = solution.transpose(matrix);
        for (int[] r : ret) {
            System.out.println(Arrays.toString(r));
        }
    }
}

interface ISolution {
    int[][] transpose(int[][] matrix);
}

class Solution implements ISolution {
    @Override
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ret = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[j][i] = matrix[i][j];
            }
        }

        return ret;
    }
}
