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

package _097_interleavingstring;

/**
 * Created by woncz on 7/20/2020.
 */
public class _097 {
    public static void main(String[] args) {
        String s1 = "cbcccbabbccbbcccbbbcabbbabcababbbbbbaccaccbabbaacbaabbbc";
        String s2 = "abcbbcaababccacbaaaccbabaabbaaabcbababbcccbbabbbcbbb";
        String s3 = "abcbcccbacbbbbccbcbcacacbbbbacabbbabbcacbcaabcbaaacbcbbbabbbaacacbbaaaabccbcbaabbbaaabbcccbcbabababbbcbbbcbb";

        ISolution solution = new Solution();

        boolean ans = solution.isInterleave(s1, s2 , s3);
        System.out.println("ans = " + ans);

        solution = new SolutionDP();
        ans = solution.isInterleave(s1, s2, s3);
        System.out.println("ans = " + ans);

        solution = new SolutionDP2();
        ans = solution.isInterleave(s1, s2, s3);
        System.out.println("ans = " + ans);
    }
}

interface ISolution {
    boolean isInterleave(String s1, String s2, String s3);
}

/**
 * 递归 + 回溯
 */
class Solution implements ISolution {
    @Override
    public boolean isInterleave(String s1, String s2, String s3) {
        return _process(s1, s2, s3);
    }

    boolean _process(String s1, String s2, String s3) {
        if (s1 == null | s2 == null || s3 == null) throw new IllegalArgumentException("error param");
        if (s1 == "" && s2 == "" && s3 == "") return true;
        if (s1.equals("")) {
            return s2.equals(s3);
        }
        if (s2.equals("")) {
            return s1.equals(s3);
        }
        if (s3.equals("")) {
            return false;
        }

        char x1 = s1.charAt(0);
        char x2 = s2.charAt(0);
        char t = s3.charAt(0);

        // 平行宇宙
        boolean r1 = false, r2 = false;
        if (x1 == t) {
            r1 = _process(s1.substring(1), s2, s3.substring(1));
        }
        if (x2 == t) {
            r2 = _process(s1, s2.substring(1), s3.substring(1));
        }

        return r1 || r2;
    }
}

class SolutionDP implements ISolution {
    @Override
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        int N = s1.length();
        int M = s2.length();
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][0] = true;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p);
                }
            }
        }
        return dp[N][M];
    }
}

/**
 * 滚动数组、状态压缩
 */
class SolutionDP2 implements ISolution {
    @Override
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        int N = s1.length();
        int M = s2.length();
        boolean[] dp = new boolean[M + 1];

        // key point
        dp[0] = true;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[M];
    }
}