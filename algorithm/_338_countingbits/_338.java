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

package _338_countingbits;

import java.util.Arrays;

/**
 * Created by woncz on 3/3/2021.
 */
public class _338 {
    public static void main(String[] args) {
        ISolution solution = new Solution();
        int num = 5;
        System.out.println(Arrays.toString(solution.countBits(num)));

        num = 2;
        System.out.println(Arrays.toString(solution.countBits(num)));

        num = 6;
        System.out.println(Arrays.toString(solution.countBits(num)));

        num = 3;
        System.out.println(Arrays.toString(solution.countBits(num)));

        num = 7;
        System.out.println(Arrays.toString(solution.countBits(num)));

        num = 0;
        System.out.println(Arrays.toString(solution.countBits(num)));

        num = 1;
        System.out.println(Arrays.toString(solution.countBits(num)));

    }
}

interface ISolution {
    int[] countBits(int num);
}

class Solution implements ISolution {
    @Override
    public int[] countBits(int num) {
        // init
        if (num == 0) return new int[] {0};
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;

        // formula
        for (int i = 2; i <= num; i++) {
            dp[i] = dp[i & (i - 1)] + 1;
        }

        return dp;
    }
}