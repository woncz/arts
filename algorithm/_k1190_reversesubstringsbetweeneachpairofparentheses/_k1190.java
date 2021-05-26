/*
 * Copyright [2021]
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

package _k1190_reversesubstringsbetweeneachpairofparentheses;

import java.util.Stack;

/**
 * Created by woncz on 5/26/2021.
 */
public class _k1190 {
    public static void main(String[] args) {
        String s = "(abcd)";
        ISolution solution = new Solution();
        System.out.println(solution.reverseParentheses(s));

        s = "(u(love)i)";
        System.out.println(solution.reverseParentheses(s));
    }
}

interface ISolution {
    String reverseParentheses(String s);
}

class Solution implements ISolution {
    @Override
    public String reverseParentheses(String s) {
        char[] cs = s.toCharArray();
        this.exchange(cs, 0, s.length() - 1, false);

         StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < cs.length - 1; i++) {
            if (cs[i] == '(' || cs[i] == ')') continue;
            sb.append(cs[i]);
        }
        return sb.toString();
    }

    void exchange(char[] cs, int left, int right, boolean change) {
        // terminate
        if (left >= right) return;

        if (!change) {
            // left
            for (int i = left; i < right; i++) {
                if (cs[i] == '(') {
                    left = i + 1;
                    break;
                }
            }

            // right
            for (int i = right; i > left; i--) {
                if (cs[i] == ')') {
                    right = i - 1;
                    break;
                }
            }
            this.exchange(cs, left, right, true);
        } else {
            while (left < right) {
                char c = cs[left];
                cs[left] = cs[right];
                cs[right] = c;
                left++;
                right--;
            }
        }
    }
}

class Solution2 implements ISolution {
    @Override
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] paired = new int[n];
        Stack<Integer> cache = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 支持嵌套
            if (s.charAt(i) == '(') {
                cache.push(i);
            } else if (s.charAt(i) == ')') {
                int j = cache.pop();
                paired[i] = j;
                paired[j] = i;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0, step = 1; i < n; i += step) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = paired[i];
                step = -step;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
