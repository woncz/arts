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

package _402_removekdigits;

import java.util.Stack;

public class _402 {
    public static void main(String[] args) {

    }
}

interface ISolution {
    String removeKdigits(String num, int k);
}

/**
 * 单调栈
 */
class Solution1 implements ISolution {
    @Override
    public String removeKdigits(String num, int k) {
        if (num == null || num.equals("") || num.length() == k) return "0";

        Stack<Character> stack = new Stack<>();

        int cnt = 0;
        while (cnt < num.length()) {
            while (k > 0 && !stack.empty() && num.charAt(cnt) < stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(cnt));
            cnt++;
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ISolution s = new Solution1();
        String num = "1432219";
        int k = 3;

        String ans = s.removeKdigits(num, k);
        System.out.println(ans);

        ans = s.removeKdigits(num, 4);
        System.out.println(ans);

        num = "11111";
        k = 3;
        ans = s.removeKdigits(num, k);
        System.out.println(ans);
    }
}

/**
 * 手动维护栈
 */
class Solution2 implements ISolution {
    @Override
    public String removeKdigits(String num, int k) {
        if (num == null || num.equals("") || num.length() <= k) return "0";

        int digits = num.length() - k;
        char[] stack = new char[num.length()];
        int top = 0;

        for (int i = 0; i < num.length(); i++) {
            while (k > 0 && top > 0 && stack[top - 1] > num.charAt(i)) {
                top--; k--;
            }
            stack[top++] = num.charAt(i);
        }
        int initial = 0;
        while (initial < digits && stack[initial] == '0') {
            initial++;
        }

        return initial == digits ? "0" : new String(stack, initial, digits - initial);
    }

    public static void main(String[] args) {
        ISolution s = new Solution2();
        String num = "1432219";
        int k = 3;

        String ans = s.removeKdigits(num, k);
        System.out.println(ans);

        ans = s.removeKdigits(num, 4);
        System.out.println(ans);

        num = "11111";
        k = 3;
        ans = s.removeKdigits(num, k);
        System.out.println(ans);
    }
}