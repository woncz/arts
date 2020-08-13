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

package _415_addstrings;

/**
 * Created by woncz on 8/3/2020.
 */
public class _415 {
    public static void main(String[] args) {
        String nums1 = "99123";
        String nums2 = "456";
        ISolution solution = new Solution2();
        System.out.println(solution.addStrings(nums1, nums2));

        nums1 = "1";
        nums2 = "9";
        solution = new Solution2();
        System.out.println(solution.addStrings(nums1, nums2));
    }
}

interface ISolution {
    String addStrings(String num1, String num2);
}

class Solution implements ISolution {
    @Override
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.equals("")) {
            return num2;
        }
        if (num2 == null || num2.equals("")) {
            return num1;
        }
        if (num1.length() < num2.length()) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }

        StringBuilder sb = new StringBuilder("");
        boolean flag = false;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int delta = num1.length() - num2.length();
            int x = flag ? 1 : 0;
            int a = num1.charAt(i) - '0';
            int b = 0;
            if (i >= delta) {
                b = num2.charAt(i - delta) - '0';
            }
            int sum = a + b + x;
            if (sum > 9) {
                flag = true;
                sb.append(sum % 10);
            } else {
                flag = false;
                sb.append(sum);
            }
        }
        if (flag) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }
}

class Solution2 implements ISolution {
    @Override
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || add != 0) {
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            int res = a + b + add;
            sb.append(res % 10);
            add = res / 10;
            i--;
            j--;
        }
        return sb.reverse().toString();
    }
}
