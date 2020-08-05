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

package _014_longestcommonprefix;

/**
 * Created by woncz on 8/3/2020.
 */
public class _014 {
    public static void main(String[] args) {
        String[] strs = new String[] {"aa", "a"};
        ISolution solution = new Solution();
        System.out.println(solution.longestCommonPrefix(strs));

        strs = new String[] {"flower","flow","flight"};
        System.out.println(solution.longestCommonPrefix(strs));

        strs = new String[] {"dog","racecar","car"};
        System.out.println(solution.longestCommonPrefix(strs));
    }
}

interface ISolution {
    String longestCommonPrefix(String[] strs);
}

/**
 * notice the inner loop and outer loop's order
 */
class Solution implements ISolution {
    @Override
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        int len = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return strs[0].substring(0, len);
                }
            }
            len++;
        }
        return strs[0];
    }
}
