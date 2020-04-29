/*
 * Copyright [2017]
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

package _008_stringtointeger;

public class _008 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String ts = "42";
        System.out.println(s.myAtoi(ts));
        ts = "   -42";
        System.out.println(s.myAtoi(ts));
        ts = "4193 with words";
        System.out.println(s.myAtoi(ts));
        ts = "words and 987";
        System.out.println(s.myAtoi(ts));
        ts = "-91283472332";
        System.out.println(s.myAtoi(ts));
    }
}

interface ISolution {
    int myAtoi(String str);
}

class Solution implements ISolution {
    @Override
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] input = str.toCharArray();
        char[] target = new char[input.length];
        int n = 0;
        Boolean sign = null;
        for (int i = 0; i < input.length; i++) {
            if (sign == null) {
                if (input[i] == ' ') continue;
                if (input[i] == '-') {
                    sign = false;
                    continue;
                }
                if (input[i] == '+') {
                    sign = true;
                    continue;
                }
                if (!this.isNumeric(input[i])) {
                    break;
                } else {
                    sign = true;
                }
            } else if (!isNumeric(input[i])){
                break;
            }
            target[n++] = input[i];
        }
        if (sign == null) return 0;
        long r = 0;
        for (int i = 0; i < n; i++) {
           r = target[i] - '0' + r * 10L;
           if (r > Integer.MAX_VALUE) return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return sign ? (int) r : (int) -r;
    }

    private boolean isNumeric(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}

class Solution2 implements ISolution {
    @Override
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;
        // 1. empty string
        if (str == null || str.length() == 0 || str.trim().length() == 0) return 0;

        // 2. remove space
        while (index < str.length() && str.charAt(index) == ' ')
            index++;

        // 3. handle sign
        if (str.charAt(index) == '-' || str.charAt(index) == '+') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        // 4. convert number
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) break;
            //check if total will be overflow after 10 times and add digit
            if(Integer.MAX_VALUE / 10 < total ||
                    Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            total = total * 10 + digit;
            index++;
        }
        return total * sign;
    }
}
