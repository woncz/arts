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

package _012_integertoroman;

public class _012 {
    public static void main(String[] args) {
        ISolution s = new Solution();
        for (int i = 1; i < 4000; i++) {
            System.out.println(i + "=" + s.intToRoman(i));
        }
    }
}

interface ISolution {
    String intToRoman(int num);
}

class Solution implements ISolution {
    @Override
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // 千
        int k = num / 1_000;
        num %= 1_000;
        sb.append(counting(k, 1_000));
        // 百
        int h = num / 100;
        num %= 100;
        sb.append(counting(h, 1_00));
        // 十
        int t = num / 10;
        num %= 10;
        sb.append(counting(t, 1_0));

        sb.append(counting(num, 1));

        return sb.toString();
    }


    String counting(int num, int unit) {
        if (unit == 1_000) {
            if (num == 1) return "M";
            if (num == 2) return "MM";
            if (num == 3) return "MMM";
        }
        if (unit == 100) {
            if (num == 1) return "C";
            if (num == 2) return "CC";
            if (num == 3) return "CCC";
            if (num == 4) return "CD";
            if (num == 5) return "D";
            if (num == 6) return "DC";
            if (num == 7) return "DCC";
            if (num == 8) return "DCCC";
            if (num == 9) return "CM";
        }
        if (unit == 10) {
            if (num == 1) return "X";
            if (num == 2) return "XX";
            if (num == 3) return "XXX";
            if (num == 4) return "XL";
            if (num == 5) return "L";
            if (num == 6) return "LX";
            if (num == 7) return "LXX";
            if (num == 8) return "LXXX";
            if (num == 9) return "XC";
        }

        if (unit == 1) {
            if (num == 1) return "I";
            if (num == 2) return "II";
            if (num == 3) return "III";
            if (num == 4) return "IV";
            if (num == 5) return "V";
            if (num == 6) return "VI";
            if (num == 7) return "VII";
            if (num == 8) return "VIII";
            if (num == 9) return "IX";
        }

        return "";
    }
}

class Solution1 implements ISolution {
    @Override
    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}