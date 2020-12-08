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

package _204_countprimes;

/**
 * Created by woncz on 12/3/2020.
 */
public class _204 {
    public static void main(String[] args) {
        int n = 499979;
        ISolution solution = new Solution2();
        System.out.println(solution.countPrimes(n));

        n = 0;
        solution = new Solution2();
        System.out.println(solution.countPrimes(n));

        n = 1;
        solution = new Solution2();
        System.out.println(solution.countPrimes(n));

        n = 499979;
        solution = new Solution2();
        System.out.println(solution.countPrimes(n));
    }
}

interface ISolution {
    int countPrimes(int n);
}

/**
 * 由近及远，积攒力量，提供方便
 */
class Solution implements ISolution {
    @Override
    public int countPrimes(int n) {
        boolean[] cache = new boolean[n];
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (cache[i] == false) {
                cnt++;
                for (int j = 2; i * j < n; j++) {
                    cache[i * j] = true;
                }
            }
        }
        return cnt;
    }
}

class Solution2 implements ISolution {
    @Override
    public int countPrimes(int n) {
        if (n < 3) return 0;

        // suppose max prime is n/2(only odd number)
        int cnt = n / 2;

        boolean[] s = new boolean[n];

        for (int i = 3; i * i < n; i+=2) {
            if (s[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += 2 * i) {
                if (!s[j]) {
                    cnt--;
                    s[j] = true;
                }
            }
        }

        return cnt;
    }
}