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

package _459_repeatedsubstringpattern;

/**
 * Created by woncz on 8/24/2020.
 */
public class _459 {
    public static void main(String[] args) {
        String s = "abc";
        ISolution solution = new Solution();
        System.out.println(solution.repeatedSubstringPattern(s));

        s = "abab";
        System.out.println(solution.repeatedSubstringPattern(s));

        s = "abcabcabcabc";
        System.out.println(solution.repeatedSubstringPattern(s));
    }
}

interface ISolution {
    boolean repeatedSubstringPattern(String s);
}

class Solution implements ISolution {
    @Override
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 1) return false;
        for (int i = 1; i <= s.length() / 2; i++) {
            if (_dfs(i, i, s)) return true;
        }
        return false;
    }

    boolean _dfs(int pattern, int cur, String s) {
        // not integer times
        if (s.length() % pattern != 0) return false;

        for (int i = 0; i < pattern; i++) {
            if (s.charAt(i) != s.charAt(cur + i)) {
                return false;
            }
        }
        int next = cur + pattern;
        if (next == s.length()) {
            return true;
        } else {
            return _dfs(pattern, next, s);
        }
    }
}
