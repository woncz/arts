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

package _020_validparentheses;

import java.util.Stack;

/**
 * Created by woncz on 8/14/2020.
 */
public class _020 {
    public static void main(String[] args) {
        String s = "()";
        ISolution solution = new Solution();
        System.out.println(solution.isValid(s));

        s = "()[]{}";
        System.out.println(solution.isValid(s));

        s = "(]";
        System.out.println(solution.isValid(s));

        s = "([)]";
        System.out.println(solution.isValid(s));

        s = "{[]}";
        System.out.println(solution.isValid(s));

    }
}

interface ISolution {
    boolean isValid(String s);
}

class Solution implements ISolution {
    @Override
    public boolean isValid(String s) {
        if (s == null || s.length() == 0)  return true;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            if (c == ')') {
                if (stack.isEmpty()) return false;
                char p = stack.pop();
                if (p != '(') return false;
            }
            if (c == ']') {
                if (stack.isEmpty()) return false;
                char p = stack.pop();
                if (p != '[') return false;
            }
            if (c == '}') {
                if (stack.isEmpty()) return false;
                char p = stack.pop();
                if (p != '{') return false;
            }
        }

        return stack.isEmpty();
    }
}