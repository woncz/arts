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

package _766_toeplitzmatrix;

/**
 * Created by woncz on 2/22/2021.
 */
public class _766 {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};

        ISolution solution = new Solution();
        boolean ans = solution.isToeplitzMatrix(matrix);
        System.out.println(ans);

        matrix = new int[][] {{1, 2}, {2, 2}};
        ans = solution.isToeplitzMatrix(matrix);
        System.out.println(ans);
    }
}

interface ISolution {
    boolean isToeplitzMatrix(int[][] matrix);
}

class Solution implements ISolution {
    @Override
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i - 1][j - 1] != matrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}

class Solution2 implements ISolution {
    @Override
    public boolean isToeplitzMatrix(int[][] matrix) {
        return false;
    }
}