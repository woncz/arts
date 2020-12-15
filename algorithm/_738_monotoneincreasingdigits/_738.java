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

package _738_monotoneincreasingdigits;

/**
 * Created by woncz on 12/15/2020.
 */
public class _738 {
    public static void main(String[] args) {
        int N = 308;
        ISolution solution = new Solution();
        System.out.println(solution.monotoneIncreasingDigits(N));

        N = 10;
        System.out.println(solution.monotoneIncreasingDigits(N));

        N = 1234;
        System.out.println(solution.monotoneIncreasingDigits(N));

        N = 332;
        System.out.println(solution.monotoneIncreasingDigits(N));

        N = 100000;
        System.out.println(solution.monotoneIncreasingDigits(N));
    }
}

interface ISolution {
    int monotoneIncreasingDigits(int N);
}

class Solution implements ISolution {
    @Override
    public int monotoneIncreasingDigits(int N) {
        if (N <= 10) return N - 1;
        int[] stat = new int[String.valueOf(N).length()];
        int i = stat.length - 1, n = N;
        while (n > 10) {
            stat[i--] = n % 10;
            n /= 10;
        }
        stat[i] = n;

        asc(stat);
        int t = stat[0];
        for (i = 1; i < stat.length; i++) {
            t = t * 10 + stat[i];
        }
        return t;
    }

    void asc(int[] stat) {
        for (int i = 1; i < stat.length; i++) {
            if (stat[i] < stat[i - 1]) {
                stat[i - 1] = stat[i - 1] - 1;
                while (i < stat.length) {
                    stat[i++] = 9;
                }
                asc(stat);
            }
        }
    }
}

class Solution2 implements ISolution {
    @Override
    public int monotoneIncreasingDigits(int N) {
        char[] stat = String.valueOf(N).toCharArray();
        int mark = stat.length;
        for (int i = stat.length - 1; i > 0; i--) {
            if (stat[i] < stat[i - 1]) {
                mark = i - 1;
                stat[i - 1]--;

            }
        }
        for (int i = mark + 1; i < stat.length; i++) {
            stat[i] = '9';
        }
        return Integer.valueOf(String.valueOf(stat));
    }
}