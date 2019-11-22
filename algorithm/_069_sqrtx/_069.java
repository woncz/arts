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

package _069_sqrtx;

public class _069 {
    public static void main(String[] args) {
        ISolution s3 = new Solution3();
        int ans = s3.mySqrt(2147483647);
        System.out.println(ans);

        ISolution s2 = new Solution2();
        ans = s2.mySqrt(2147395599);
        System.out.println(ans);

    }
}

interface ISolution {
    int mySqrt(int x);
}

/**
 * 二分法
 */
class Solution1 implements ISolution {
    @Override
    public int mySqrt(int x) {
        long left = 0, right = x;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (mid * mid == x) return (int)mid;
            if (mid * mid < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int)right;
    }
}

/**
 * 牛顿法
 * 注意：精度不能过于精确，precision=1E-15，传入8后，系统死循环
 */
class Solution2 implements ISolution {
    @Override
    public int mySqrt(int x) {
        if (x == 0) return x;
        long i = x;
        while (i > x / i) {
            i = (i + x / i) / 2;
        }
        return (int)i;
    }

    public int mySqrt(double x) {
        if (x == 0) return 0;
        double precision = 1E-9;
        double i = 1.0;
        while (Math.abs(i * i - x) > precision) {
            i = (i + x / i) / 2;
        }
        return (int)i;
    }

    public static void main(String[] args) {
        Solution2 s2 = new Solution2();
        int ans = s2.mySqrt(8.0);
        System.out.println(ans);

        ans = s2.mySqrt(2147395599);
        System.out.println(ans);
    }
}

/**
 * 暴力法
 */
class Solution3 implements ISolution {
    @Override
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        for (long i = 0; i <= x; i++) {
            if (i * i > x) return (int) (i - 1);
        }
        return 0;
    }
}


