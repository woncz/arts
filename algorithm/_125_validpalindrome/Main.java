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

package _125_validpalindrome;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String ts = "A man, a plan, a canal: Panama";
        System.out.println(s.isPalindrome(ts));

        ts = "race a car";
        System.out.println(s.isPalindrome(ts));

        Integer a = -1;

        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2));

        System.out.println(Integer.toBinaryString(-1 & -2));


        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-1 & 1));
    }
}

class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.equals("")) return true;
        s = s.toLowerCase();

        // init pointer
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char c1 = s.charAt(left);
            while (!isValid(c1) && left < right) {
                left++;
                c1 = s.charAt(left);
            }
            char c2 = s.charAt(right);
            while (!isValid(c2) && right > left) {
                right--;
                c2 = s.charAt(right);
            }
            if (c1 == c2) {
                left++; right--;
            } else {
                break;
            }
        }
        return left >= right - 1;
    }

    private boolean isValid(char c) {
        return c >= 'a' && c <= 'z';
    }
}
