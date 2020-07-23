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

package _718_maximumlengthofrepeatedsubarray;

/**
 * Created by woncz on 7/22/2020.
 */
public class _718 {
    public static void main(String[] args) {
        int[] A = new int[] {1,2,4,3,9,2,1,4};
        int[] B = new int[] {4,3,2,1,4,7};
        ISolution solution = new Solution();
        System.out.println(solution.findLength(A, B));
    }
}

interface ISolution {
    int findLength(int[] A, int[] B);
}

class Solution implements ISolution {
    @Override
    public int findLength(int[] A, int[] B) {
        int M = A.length;
        int N = B.length;
        int[][] dp = new int[M][N];
        int ans = 0;

        // init
        for (int i = 0; i < M; i++) {
            if (A[i] == B[0]) {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < N; j++) {
            if (B[j] == A[0]) {
                dp[0][j] = 1;
            }
        }

        int max = 0;
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                int a = A[i];
                int b = B[j];
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
