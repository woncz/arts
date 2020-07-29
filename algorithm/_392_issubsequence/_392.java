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

package _392_issubsequence;

/**
 * Created by woncz on 7/27/2020.
 */
public class _392 {
    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";
        ISolution solution = new Solution();
        System.out.println(solution.isSubsequence(s, t));

        s = "axc"; t = "ahbgdc";
        System.out.println(solution.isSubsequence(s, t));

    }
}

interface ISolution {
    boolean isSubsequence(String s, String t);
}

class Solution implements ISolution {
    @Override
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.equals("")) return true;
        if (t == null || s.equals("")) return false;

        int M = s.length();
        int N = t.length();

        int i = 0, j = 0;
        for (; i < M && j < N;) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == M;
    }
}