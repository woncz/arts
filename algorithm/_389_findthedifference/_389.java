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

package _389_findthedifference;

/**
 * Created by woncz on 12/18/2020.
 */
public class _389 {
    public static void main(String[] args) {
        String s = "abcd", t = "abcde";
        ISolution solution = new Solution3();
        System.out.println(solution.findTheDifference(s, t));

        s = ""; t = "y";
        System.out.println(solution.findTheDifference(s, t));

        s = "a"; t = "aa";
        System.out.println(solution.findTheDifference(s, t));

        s = "ae"; t = "aea";
        System.out.println(solution.findTheDifference(s, t));

    }
}

interface ISolution {
    char findTheDifference(String s, String t);
}

/**
 * 登记法
 */
class Solution implements ISolution {
    @Override
    public char findTheDifference(String s, String t) {
        if (s == null || s.length() == 0) return t.charAt(0);

        int[] stat = new int[26];
        for (int i = 0; i < s.length(); i++) {
            stat[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (stat[t.charAt(i) - 'a'] == 0) {
                return t.charAt(i);
            }
            stat[t.charAt(i) - 'a']--;
        }

        return 0;
    }
}

/**
 * 求和法
 */
class Solution2 implements ISolution {
    @Override
    public char findTheDifference(String s, String t) {
        int as = 0, at = 0;
        for (char c : s.toCharArray()) {
            as += c;
        }
        for (char c : t.toCharArray()) {
            at += c;
        }
        return (char)(at - as);
    }
}

/**
 * 异或法
 */
class Solution3 implements ISolution {
    @Override
    public char findTheDifference(String s, String t) {
        char ans = 0;
        for (char c : s.toCharArray()) {
            ans ^= c;
        }
        for (char c : t.toCharArray()) {
            ans ^= c;
        }
        return ans;
    }
}