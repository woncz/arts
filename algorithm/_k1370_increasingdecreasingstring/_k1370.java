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

package _k1370_increasingdecreasingstring;

/**
 * Created by woncz on 11/25/2020.
 */
public class _k1370 {
    public static void main(String[] args) {
        String s = "aaaabbbbcccc";
        ISolution solution = new Solution();
        System.out.println(solution.sortString(s));
    }
}

interface ISolution {
    String sortString(String s);
}

class Solution implements ISolution {
    @Override
    public String sortString(String s) {
        if (s == null || s.length() <= 1) return s;

        int[] cache = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cache[s.charAt(i) - 'a']++;
        }

        int cnt = 0;
        StringBuffer sb = new StringBuffer("");
        while (cnt <= s.length()) {
            for (int j = 0; j < 26; j++) {
                if (cache[j] > 0) {
                    sb.append((char)('a' + j));
                    cache[j]--;
                }
            }
            for (int j = 25; j >= 0; j--) {
                if (cache[j] > 0) {
                    sb.append((char)('a' + j));
                    cache[j]--;
                }
            }
            cnt++;
        }

        return sb.toString();
    }
}
