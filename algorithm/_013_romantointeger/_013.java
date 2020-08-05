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

package _013_romantointeger;

/**
 * Created by woncz on 8/3/2020.
 */
public class _013 {
    public static void main(String[] args) {
        String s = "III";
        ISolution solution = new Solution();
        System.out.println(solution.romanToInt(s));

        s = "IV";
        System.out.println(solution.romanToInt(s));

        s = "IX";
        System.out.println(solution.romanToInt(s));

        s = "LVIII";
        System.out.println(solution.romanToInt(s));

        s = "MCMXCIV";
        System.out.println(solution.romanToInt(s));
    }
}

interface ISolution {
    int romanToInt(String s);
}

class Solution implements ISolution {
    @Override
    public int romanToInt(String s) {
        int sum = 0;
        int i = 0;
        while (i < s.length()) {
            char x = s.charAt(i);
            if (x == 'M' && (i > 0 && s.charAt(i - 1) != 'C' || i == 0)) {
                sum += 1_000;
                i++;
                continue;
            }
            if (x == 'C' && i < s.length() - 1) {
                if (s.charAt(i + 1) == 'M') {
                    sum += 900;
                    i++;
                    i++;
                    continue;
                }
                if (s.charAt(i + 1) == 'D') {
                    sum += 400;
                    i++;
                    i++;
                    continue;
                }

            }
            if (x == 'D') {
                sum += 500;
                i++;
                continue;
            }
            if (x == 'C') {
                sum += 100;
                i++;
                continue;
            }

            if (x == 'X' && i < s.length() - 1) {
                if (s.charAt(i + 1) == 'C') {
                    sum +=  90;
                    i++;
                    i++;
                    continue;
                }
                if (s.charAt(i + 1) == 'L') {
                    sum += 40;
                    i++;
                    i++;
                    continue;
                }
            }
            if (x == 'L') {
                sum += 50;
                i++;
                continue;
            }
            if (x == 'X') {
                sum += 10;
                i++;
                continue;
            }

            if (x == 'I' && i < s.length() - 1) {
                if (s.charAt(i + 1) == 'X') {
                    sum += 9;
                    i++;
                    i++;
                    continue;
                }
                if (s.charAt(i + 1) == 'V') {
                    sum += 4;
                    i++;
                    i++;
                    continue;
                }
            }

            if (x == 'V') {
                sum += 5;
                i++;
                continue;
            }
            if (x == 'I') {
                sum += 1;
                i++;
                continue;
            }
        }
        return sum;
    }
}
