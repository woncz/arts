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

package _096_uniquebinarysearchtrees;

/**
 * Created by woncz on 7/15/2020.
 */
public class _096 {
    public static void main(String[] args) {
        ISolution solution = new Solution();
        int n = 3;
        for (int i = 0; i <= n; i++) {
            System.out.println(solution.numTrees(i));
        }

        solution = new Catalan();
        for (int i = 0; i <= n; i++) {
            System.out.println(solution.numTrees(i));
        }
    }
}

interface ISolution {
    int numTrees(int n);
}

/**
 * DP(x) = sum(F(i, n))
 * F(i, n) = G(i - 1) * G(n - i)
 */
class Solution implements ISolution {

    int[] cache;

    @Override
    public int numTrees(int n) {
        if (n < 2) return 1;
        cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        return _process(n);
    }

    int _process(int n) {
        if (cache[n] > 0) return cache[n];
        int cnt = 0;
        for (int i = 0; i < n / 2; i++) {
            cnt += 2 * _process(i) * _process(n - i - 1);
        }
        if (n % 2 == 1) {
            cnt += _process(n / 2) * _process(n / 2);
        }
        cache[n] = cnt;
        return cnt;
    }

    int _process2(int n) {
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                cache[i] += cache[j - 1] * cache[i - j];
            }
        }
        return cache[n];
    }
}

class Catalan implements ISolution {
    @Override
    public int numTrees(int n) {
        long C = 1;
        for (int i = 0; i < n; i++) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int)C;
    }
}

