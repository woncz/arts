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

package _115_distinctsubsequences;

public class _115 {
    public static void main(String[] args) {
        String S = "rabbbit", T = "rabbit";
        ISolution s = new Solution();
        int ans = s.numDistinct(S, T);
        System.out.println(ans);
    }

}

interface ISolution {
    int numDistinct(String s, String t);
}

class Solution implements ISolution {
    @Override
    public int numDistinct(String s, String t) {
        int M = t.length();
        int N = s.length();
        int[][] dp = new int[M + 1][N + 1];

        // init
        for (int i = 0; i <= N; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[M][N];
    }
}
