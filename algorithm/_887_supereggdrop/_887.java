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

package _887_supereggdrop;

import java.util.HashMap;
import java.util.Map;

public class _887 {
    public static void main(String[] args) {
        ISolution s = new Solution3();

        int K = 1, N = 2;
        int ans = s.superEggDrop(K, N);
        System.out.println("K=" + K + ", N=" + N + ", res=" + ans);

        assert ans == 2;

        K = 2; N = 6;
        ans = s.superEggDrop(K, N);
        System.out.println("K=" + K + ", N=" + N + ", res=" + ans);

        assert ans == 3;

        K = 2; N = 7;
        ans = s.superEggDrop(K, N);
        System.out.println("K=" + K + ", N=" + N + ", res=" + ans);

        assert ans == 3;

        K = 2; N = 4;
        ans = s.superEggDrop(K, N);
        System.out.println("K=" + K + ", N=" + N + ", res=" + ans);

        assert ans == 3;


        K = 3; N = 14;
        ans = s.superEggDrop(K, N);
        System.out.println("K=" + K + ", N=" + N + ", res=" + ans);
        assert ans == 4;

        K = 2; N = 9;
        ans = s.superEggDrop(K, N);
        System.out.println("K=" + K + ", N=" + N + ", res=" + ans);
        assert ans == 4;
    }
}

interface ISolution {
    int superEggDrop(int K, int N);
}

/**
 * wrong answer
 */
class Solution implements ISolution {
    @Override
    public int superEggDrop(int K, int N) {
        return drop(K, N);
    }

    private int drop(int k, int n) {
        // terminator
        if (n == 1 || n == 2) return n;

        // only one eggs
        if (k == 1) return n;

        // drill down
        return Math.max(drop(k - 1, (n + 1) / 2 - 1), drop(k , n / 2)) + 1;
    }
}

class Solution1 implements ISolution {
    @Override
    public int superEggDrop(int K, int N) {
        int dp[] = new int[K + 1], m = 0;
        for (m = 0; dp[K] < N; m++) {
            for (int k = K; k > 0; k--) {
                dp[k] += dp[k - 1] + 1;
            }
        }
        return m;
    }
}

class Solution2 implements ISolution {
    @Override
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        while (dp[m][K] < N) {
            m++;
            for (int k = 1; k <= K; k++) {
                dp[m][k] = dp[m - 1][k] + dp[m - 1][k - 1] + 1;
            }
        }
        return m;
    }
}

class Solution3 implements ISolution {
    @Override
    public int superEggDrop(int K, int N) {
        return dp(K, N);
    }

    // 备忘录，缓存结果提高计算效率，空间换时间
    Map<String, Integer> memo = new HashMap<>();

    int dp(int k, int n) {
        // terminator
        String key = k + ":" + n;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // drill down
        int ans;
        if (n == 0) {
            ans = 0;
        } else if (k == 1) {
            ans = n;
        } else {
            int lo = 1, hi = n;
            while (lo + 1 < hi) {
                int next = (lo + hi) / 2;
                int t1 = dp(k - 1, next - 1);
                int t2 = dp(k, n - next);
                if (t1 < t2) {
                    lo = next;
                } else if (t1 > t2) {
                    hi = next;
                } else {
                    lo = hi = next;
                }
            }

            ans = 1 + Math.min(Math.max(dp(k - 1, lo - 1), dp(k, n - lo)),
                    Math.max(dp(k - 1, hi - 1), dp(k, n - hi)));
        }
        memo.put(key, ans);
        return memo.get(key);
    }
}
