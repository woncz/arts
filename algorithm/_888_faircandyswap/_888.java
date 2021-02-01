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

package _888_faircandyswap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by woncz on 2/1/2021.
 */
public class _888 {
    public static void main(String[] args) {
        int[] A= new int[] {1, 1}, B = new int[] {2, 2};
        ISolution solution = new Solution();
        int[] ans = solution.fairCandySwap(A, B);
        System.out.println(Arrays.toString(ans));

        A = new int[] {1, 2, 5}; B = new int[] {2, 4};
        ans = solution.fairCandySwap(A, B);
        System.out.println(Arrays.toString(ans));
    }
}

interface ISolution {
    int[] fairCandySwap(int[] A, int[] B);
}

class Solution implements ISolution {
    @Override
    public int[] fairCandySwap(int[] A, int[] B) {
        int sa = 0, sb = 0;
        Set<Integer> ssb = new HashSet<>();
        for (int a : A) {
            sa += a;
        }
        for (int b : B) {
            sb += b;
            ssb.add(b);
        }

        int delta = (sa - sb ) / 2;

        for (int a : A) {
            if (ssb.contains(a - delta)) {
                return new int[] {a, a - delta};
            }
        }
        return null;
    }
}
