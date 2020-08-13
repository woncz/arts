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

package _043_multiplystrings;

import java.util.Random;

/**
 * Created by woncz on 8/13/2020.
 */
public class _043 {
    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "89";
        ISolution solution = new Solution();
        System.out.println(solution.multiply(num1, num2));

        Random r = new Random();
        long sum = 0;
        for (int i = 0; i < 100; i++) {
            int m1 = r.nextInt(100000);
            int m2 = r.nextInt(1000000);
            sum = 1L * m1 * m2;

            long sum2 = Long.parseLong(solution.multiply(m1 + "", m2 + ""));
            if (sum != sum2) {
                throw new IllegalStateException("error");
            }
        }
    }
}

interface ISolution {
    String multiply(String num1, String num2);
}

/**
 * 特别注意最高位的进位情况
 */
class Solution implements ISolution {
    @Override
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return null;
        if ("0".equals(num1) || "0".equals(num2)) return "0";

        String sum = "";
        for (int i = 0; i < num1.length(); i++) {
            String m = _multipy(num2, num1.charAt(i));
             sum = _add(sum + "0", m);
        }

        return sum;
    }

    String _multipy(String s1, char c2) {
        StringBuilder ret = new StringBuilder();
        int bit = 0;
        for (int i = s1.length() - 1; i >= 0; i--) {
            char c1 = s1.charAt(i);
            int sum = (c1 - '0') * (c2 - '0')  + bit;
            bit = sum / 10;
            ret.append(sum % 10);
        }
        if (bit > 0) {
            ret.append(bit);
        }
        return ret.reverse().toString();
    }

    String _add(String s1, String s2) {
         if (s1.length() < s2.length()) {
             String tmp = s1;
             s1 = s2;
             s2 = tmp;
         }

         StringBuilder sb = new StringBuilder();
         int bit = 0;
         int l1 = s1.length();
         int l2 = s2.length();
         for (int i = 0; i < s1.length(); i++) {
             char c1 = s1.charAt(l1 - 1 - i);
             char c2 = '0';
             if (i < l2) {
                 c2 = s2.charAt(l2 - 1 - i);
             }
             int sum = (c1 - '0') + (c2 - '0') + bit;
             sb.append(sum % 10);
             bit = sum / 10;
         }
         if (bit != 0) {
             sb.append(bit);
         }
         return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String sum = s._add("", "3456");
        System.out.println(sum);
    }
}
