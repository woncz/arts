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

package _387_firstuniquecharacterinastring;

/**
 * Created by woncz on 12/23/2020.
 */
public class _387 {
    public static void main(String[] args) {
        String s = "leetcode";
        ISolution solution = new Solution();
        System.out.println(solution.firstUniqChar(s));

        s = "loveleetcode";
        System.out.println(solution.firstUniqChar(s));

        s = "cc";
        System.out.println(solution.firstUniqChar(s));
    }
}

interface ISolution {
    int firstUniqChar(String s);
}

class Solution implements ISolution {
    @Override
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        int[] stat = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            stat[index]++;
        }
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (stat[index] == 1) return i;
        }
        return -1;
    }
}