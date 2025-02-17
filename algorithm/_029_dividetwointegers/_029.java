package _029_dividetwointegers;

public class _029 {
    public static void main(String[] args) {
        ISolution s = new Solution();
        System.out.println(s.divide(10, 3));

        s = new Solution2();
        System.out.println(s.divide(10, 3));

    }
}

interface ISolution {
    int divide(int dividend, int divisor);
}

class Solution implements ISolution {
    @Override
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        if (dividend == 0) {
            return 0;
        }

        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = true;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }
        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            boolean check = quickAdd(divisor, mid, dividend);
            if (check) {
                ans = mid;
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return rev ? -ans : ans;
    }

    boolean quickAdd(int y, int z, int x) {
        int result = 0, add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                if (result < x - add) {
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            z >>= 1;
        }
        return true;
    }

}

class Solution2 implements ISolution {

    int INF = Integer.MAX_VALUE;

    @Override
    public int divide(int dividend, int divisor) {
        long a = dividend, b = divisor;
        boolean neg = false;
        if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
            neg = true;
        }
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        long l = 0, r = a;
        while (l < r) {
            long mid = (l + r + 1) >> 1;
            if (quickAdd(mid, b) <= a) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        r = neg? -r : r;
        if (r > INF || r < -INF - 1) r = INF;
        return (int)r;
    }

    long quickAdd(long b, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) {
                ans += b;
            }
            b += b;
            k >>= 1;
        }
        return ans;
    }
}
