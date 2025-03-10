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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 8/13/2020.
 */
public class _017 {
    public static void main(String[] args) {
        String digits = "23";
        ISolution solution = new Solution4();
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

class Solution3 implements ISolution {

    String[] alpha = new String[] {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> ans = new LinkedList<>();

    @Override
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return Collections.emptyList();
        ans.clear();
        this._dfs(0, new StringBuilder(""), digits);
        return ans;
    }

    void _dfs(int index, StringBuilder holder, String digits) {
        if (index == digits.length()) {
            ans.add(holder.toString());
            return;
        }

        int d = digits.charAt(index) - '2';
        String candidate = alpha[d];

        for (char c : candidate.toCharArray()) {
            holder.append(c);
            this._dfs(index + 1, holder, digits);
            holder.deleteCharAt(holder.length() - 1);
        }
    }
}

class Solution4 implements ISolution {
    String[] alpha = new String[] {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    @Override
    public List<String> letterCombinations(String digits) {
        if (digits == null ||digits.isEmpty()) return Collections.EMPTY_LIST;
        List<String> ans = new LinkedList<>();
        this._dfs(ans, 0, new StringBuffer(""), digits);
        return ans;
    }

    void _dfs(List<String> ans, int index, StringBuffer prefix, String digits) {
        // terminator
        if (index == digits.length()) {
            ans.add(prefix.toString());
            return;
        }

        int d = digits.charAt(index) - '2';
        String candidate = alpha[d];
        for (char c : candidate.toCharArray()) {
            prefix.append(c); // 选择
            this._dfs(ans, index + 1, prefix, digits);
            prefix.deleteCharAt(prefix.length() - 1); // 恢复现场
        }
    }
}

class Solution5 implements ISolution {
    private List<String> ans = new ArrayList<>();
    private char[] path;
    private char[] digits;

    private String[] alpha = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    @Override
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return List.of();
        this.path = new char[digits.length()];
        this.digits = digits.toCharArray();

        _dfs(0);

        return ans;
    }

    // 遍历 or 构造子问题
    void _dfs(int i) {
        // terminator
        if (i == digits.length) {
            ans.add(new String(path));
            return;
        }

        // drill down
        int d = digits[i] - '0';
        String candidate = alpha[d];
        for (char c : candidate.toCharArray()) {
            this.path[i] = c;
            _dfs(i + 1);
            // 不必恢复现场，因为每次循环都是在相同位置：path[i]放置不同的字符，而且往更深层递归时，上一层的path[i]是必须填值的
            // 此处也没有选 或者 不选的问题，因为是必选
        }
    }
}