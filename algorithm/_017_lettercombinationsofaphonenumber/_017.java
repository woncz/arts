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

package _017_lettercombinationsofaphonenumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by woncz on 8/13/2020.
 */
public class _017 {
    public static void main(String[] args) {
        String digits = "23";
        ISolution solution = new Solution2();
        System.out.println(solution.letterCombinations(digits));

        System.out.println(solution.letterCombinations("9"));
        System.out.println(solution.letterCombinations("99"));
    }
}

interface ISolution {
    List<String> letterCombinations(String digits);
}

class Solution implements ISolution {

    String[] board = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    @Override
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return Collections.emptyList();

        return _dfs(digits);
    }

    List<String> _dfs(String digits) {
        if (digits == null || digits.length() == 0) return Collections.emptyList();

        List<String> ans = new ArrayList<>();
        String s = board[digits.charAt(0) - '0'];
        for (char c : s.toCharArray()) {
            if (digits.length() == 1) {
                ans.add(c + "");
            } else {
                for (String ss : _dfs(digits.substring(1))) {
                    ans.add(c + ss);
                }
            }
        }
        return ans;
    }
}

class Solution2 implements ISolution {

    String[] board = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> ans = new ArrayList<>();

    @Override
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return Collections.emptyList();
        ans.clear();
        _backtrack("", digits);
        return ans;
    }

    void _backtrack(String cur, String next) {
        if (next.length() == 0) {
            ans.add(cur);
        } else {
            String letters = board[next.charAt(0) - '0'];
            for (char c : letters.toCharArray()) {
                _backtrack(cur + c, next.substring(1));
            }
        }
    }
}