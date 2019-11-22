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

package _367_validperfectsquare;

public class _367 {
    public static void main(String[] args) {

    }
}

interface ISolution {
    boolean isPerfectSquare(int num);
    default double sqrt(double x) {
        double i = 1.0;
        double precision = 1E-9;
        while (Math.abs(i * i - x) > precision) {
            i = (i + x / i) / 2;
        }
        return i;
    }
}

class Solution1 implements ISolution {
    @Override
    public boolean isPerfectSquare(int num) {
        double d = this.sqrt(num);
        return Math.abs(d - (int)d) < 1E-9;
    }

    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        boolean ans = s1.isPerfectSquare(16);
        System.out.println(ans);
    }
}

class Solution2 implements ISolution {
    @Override
    public boolean isPerfectSquare(int num) {
        if (num == 0) return true;
        int left = 1, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long result = 1L * mid * mid;
            if (result == num) return true;
            if (result < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution2 s2 = new Solution2();
        boolean ans = s2.isPerfectSquare(16);
        System.out.println(ans);

        ans = s2.isPerfectSquare(2147483647);
        System.out.println(ans);
    }
}


