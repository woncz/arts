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

package _647_palindromicsubstrings;

/**
 * Created by woncz on 8/19/2020.
 */
public class _647 {
    public static void main(String[] args) {
        String s = "abc";
        ISolution solution = new Solution();
        System.out.println(solution.countSubstrings(s));

        s = "aaa";
        System.out.println(solution.countSubstrings(s));
    }
}

interface ISolution {
    int countSubstrings(String s);
}

class Solution implements ISolution {
    int cnt = 0;
    @Override
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        int N = s.length();

        boolean[][] dp = new boolean[N][N];

        this._dfs(s, dp, 0, N - 1);

        return cnt;
    }

    boolean _dfs(String s, boolean[][] dp, int left, int right) {
        if (left == right) dp[left][right] = true;
        else dp[left][right] = s.charAt(left) == s.charAt(right) && (right <= left + 1 || _dfs(s, dp, left + 1, right - 1));

        if (dp[left][right]) cnt++;
        return dp[left][right];
    }
}

class Solution2 implements ISolution {
    int cnt = 0;
    @Override
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int N = s.length();

        boolean[][] dp = new boolean[N][N];
        cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                dp[i][j] = _dfs(s, i, j);
                if (dp[i][j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    boolean _dfs(String s, int left, int right) {
        if (left == right) {
            return true;
        }
        return s.charAt(left) == s.charAt(right) && (right <= left + 1 || _dfs(s, left + 1, right - 1));
    }

    // dp[i][j] = dp[i - 1][j - 1] + s.charAt(i) == s.charAt(j)
}