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

package _304_rangesumquery2dimmutable;

/**
 * Created by woncz on 3/2/2021.
 */
public class _304 {
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix nm = new NumMatrix(matrix);
        int row1 = 2, col1 = 1, row2 = 4, col2 = 3;
        System.out.println(nm.sumRegion(row1, col1, row2, col2));

        row1 = 1; col1 = 1; row2 = 2; col2 = 2;
        System.out.println(nm.sumRegion(row1, col1, row2, col2));


        Matrix m = new Matrix(matrix);
        System.out.println(m.sumRegion(row1, col1, row2, col2));
    }
}


// method 1
class NumMatrix {

    int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        if (row1 < 0 || row1 >= matrix.length || row2 < 0 || row2 >= matrix.length) {
            throw new IllegalArgumentException("");
        }
        if (col1 < 0 || col1 > matrix[0].length || col2 < 0 || col2 >= matrix[0].length) {
            throw new IllegalArgumentException("");
        }

        int s = 0;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                s += matrix[i][j];
            }
        }
        return s;
    }
}

class Matrix {
    // 二维数组前缀和
    int[][] dp;
    public Matrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length, n = matrix[0].length;

        dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] + dp[row1][col1] - dp[row1][col2 + 1] - dp[row2 + 1][col1];
    }
}