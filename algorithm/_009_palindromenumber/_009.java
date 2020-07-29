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

package _009_palindromenumber;

/**
 * Created by woncz on 7/28/2020.
 */
public class _009 {
    public static void main(String[] args) {
        ISolution solution = new Solution();
        System.out.println(solution.isPalindrome(121));
        System.out.println(solution.isPalindrome(-121));
        System.out.println(solution.isPalindrome(10));

        solution = new Solution2();
        System.out.println(solution.isPalindrome(121));
        System.out.println(solution.isPalindrome(-121));
        System.out.println(solution.isPalindrome(10));
    }
}

interface ISolution {
    boolean isPalindrome(int x);
}

class Solution implements ISolution {
    @Override
    public boolean isPalindrome(int x) {
        String s = x + "";
        for (int i = 0; i <= s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        }
        return true;
    }
}

class Solution2 implements ISolution {
    @Override
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        final int xx = x;
        long r = x % 10;
        while ( (x /= 10) != 0) {
            r = r * 10 + x % 10;
        }

        return r == xx;
    }
}