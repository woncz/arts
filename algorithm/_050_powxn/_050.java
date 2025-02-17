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

package _050_powxn;

public class _050 {
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);

        int n = Integer.MIN_VALUE;

        System.out.println(n >>> 1);

        n = Integer.MAX_VALUE;
        System.out.println(n >>> 1);

        Solution1 s1 = new Solution1();
        double d = s1.myPow(2, 10);
        System.out.println(d);

        Solution2 s2 = new Solution2();
        d = s2.myPow(2, 10);
        System.out.println(d);

        d = s2.myPow(2, -10);
        System.out.println(d);
    }
}

interface ISolution {
    public double myPow(double x, int n);
}

/**
 * 暴力法
 * O(N)
 */
class Solution1 implements ISolution {
    @Override
    public double myPow(double x, int n) {
        double res = 1.0;
        for (int i = 0; i < n; i++) {
            res *= x;
        }
        return res;
    }
}


/**
 * 分治法
 * 特别需要注意 n = Integer.MIN_VALUE的时候，此时 -n会溢出
 * O(logN)
 */
class Solution2 implements ISolution {
    @Override
    public double myPow(double x, int n) {
        return pow(x, n);
    }

    private double pow(double x, long n) {
        // terminator
        if (n == 0) {
            return 1.0;
        }
        // process data
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        // drill down
        double res = pow(x, n / 2);

        // n & 1 等价于 n % 2 == 1
        return (n & 1) == 1 ? res * res * x : res * res;
    }
}

class Solution3 implements ISolution {
    @Override
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (n < 0 ) {
            x = 1 / x;
        }
        double res = myPow(x, n >>> 1);
        return (n & 1) == 1 ? res * res * x : res * res;
    }
}

class Solution4 implements ISolution {
    @Override
    public double myPow(double x, int n) {
        long N = n;
        return N > 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    /**
     * 快速乘法 quick multiply (递归)
     */
    private double quickMul(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double ans = quickMul(x, n / 2);
        //return n % 2 == 0? ans * ans : ans * ans * x;
        return (n & 1) == 0 ? ans * ans : ans * ans * x ;
    }
}

class Solution5 implements ISolution {
    @Override
    public double myPow(double x, int n) {
        return (long) n > 0? quickMul(x, n) : 1.0 / quickMul(x, -(long) n);
    }

    /**
     * 快速乘法 quick multiply (迭代)
     */
    private double quickMul(double x, long N) {
        double ans = 1.0;
        while (N > 0) {
            if ((N & 1) == 1) {
                ans *= x;
            }
            x *= x;
            N >>= 1;
        }
        return ans;
    }
}