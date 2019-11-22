/*
 * Copyright [2017]
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

package _010_regularexpressionmatching;

public class _010 {
    public static void main(String[] args) {
        Solution ss = new Solution();
        String s = "aa";
        String p = "a";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution();
        s = "aa";
        p = "a*";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution();
        s = "ab";
        p = ".*";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution();
        s = "aab";
        p = "c*a*b";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution();
        s = "mississippi";
        p = "mis*is*p*.";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution();
        s = "aaa";
        p = "a*a";
        System.out.println(ss.isMatch(s, p));

        System.out.println("-----------------------------------------");

        testSolution2();

        System.out.println("-----------------------------------------");
        testSolution3();
    }

    public static void testSolution2() {
        Solution2 ss = new Solution2();
        String s = "aa";
        String p = "a";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution2();
        s = "aa";
        p = "a*";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution2();
        s = "ab";
        p = ".*";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution2();
        s = "aab";
        p = "c*a*b";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution2();
        s = "mississippi";
        p = "mis*is*p*.";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution2();
        s = "aaa";
        p = "a*a";
        System.out.println(ss.isMatch(s, p));
    }

    public static void testSolution3() {
        Solution3 ss = new Solution3();
        String s = "aa";
        String p = "a";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution3();
        s = "aa";
        p = "a*";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution3();
        s = "ab";
        p = ".*";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution3();
        s = "aab";
        p = "c*a*b";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution3();
        s = "mississippi";
        p = "mis*is*p*.";
        System.out.println(ss.isMatch(s, p));

        ss = new Solution3();
        s = "aaa";
        p = "a*a";
        System.out.println(ss.isMatch(s, p));
    }

}

class Solution {

    private boolean matched = false;

    public boolean isMatch(String s, String p) {
        // special case
        if (s == null || s.length() == 0) {
            if (p == null || p.length() == 0) {
                return true;
            } else {
                int plen = p.length();
                if ((plen & 1) == 1) return false;
                for (int i = plen - 2; i >= 0; i -= 2) {
                    if (p.charAt(i) != '*') return false;
                }
                return true;
            }
        } else {
            if (p == null || p.length() == 0) return false;
        }

        // normal case
        this.match(s, p, 0, 0);

        return matched;
    }

    private void match(String s, String p, int i, int j) {
        if (matched) return;
        if (p.length() == j) {
            if (s.length() == i) matched = true;
            return;
        }

        if (j + 1 < p.length() && p.charAt(j + 1) == '*') { // 匹配任意字符
            while (i < s.length()) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    i++;
                    this.match(s, p, i, j + 2);
                } else {
                    break;
                }
            }
            this.match(s, p, i, j + 2);
        } else if (p.charAt(j) == '.') { // 匹配任意1个字符
            this.match(s, p, i + 1, j + 1);
        } else if (i < s.length() && s.charAt(i) == p.charAt(j)) { // 相同字符
            this.match(s, p, i + 1, j + 1);
        }

    }
}

class Solution2 {

    public boolean isMatch(String s, String p) {
        boolean[][] dp;
        int n = s.length();
        int m = p.length();

        // dp状态定义
        dp = new boolean[n + 1][m + 1];
        dp[n][m] = true;

        // dp状态转移方程：从后往前
        for (int i = n; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                boolean naiveMatch = i < n && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
                if (j + 1 < m && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || naiveMatch && dp[i + 1][j];
                } else {
                    dp[i][j] = naiveMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}

class Solution3 {
    public boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
