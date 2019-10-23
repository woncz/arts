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

package _007_reverseinteger;

/**
 * 特别注意边界值
 *
 * @author woncz
 * @date 9/4/2019
 */
public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int t = 123;
        System.out.println(s.reverse(t));

        t = -123;
        System.out.println(s.reverse(t));

        t = 120;
        System.out.println(s.reverse(t));

        t = -1;
        System.out.println(s.reverse(t));

        t = 1534236469;
        System.out.println(s.reverse(t));
    }
}

class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return x;
        }
        boolean minus = false;
        if (x < 0) {
            minus = true;
            x = -x;
        }

        // 递归
        long r = 0;
        while (x > 0) {
            r = r * 10 + x % 10;
            x = x / 10;
        }

        if (r > Integer.MAX_VALUE) {
            return 0;
        } else {
            return (int) (minus ? -r : r);
        }
    }
}
