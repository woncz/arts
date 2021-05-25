/*
 * Copyright [2021]
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

package _k1787_makethexorofallsegmentsequaltozero;

import java.util.Arrays;

/**
 * Created by woncz on 5/25/2021.
 */
public class _k1787 {
    public static void main(String[] args) {
        int[] nums = {1,2,0,3,0};
        int k = 1;
        ISolution solution = new Solution();
        System.out.println(solution.minChanges(nums, k));

        nums = new int[] {3,4,5,2,1,7,3,4,7};
        k = 3;
        System.out.println(solution.minChanges(nums, k));
    }
}


interface ISolution {
    int minChanges(int[] nums, int k);
}

class Solution implements ISolution {
    @Override
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[][] stat = new int[k][1 << 10];

        for (int i = 0; i < n; i++) {
            stat[i % k][nums[i]]++;
        }
        int[][] dp = new int[k + 1][1 << 10];
        for (int[] d : dp) {
            Arrays.fill(d, n);
        }

        dp[0][0] = 0;
        int bestUpToLast = 0;
        for (int i = 0; i < k; i++) {
            int bestToi = Integer.MAX_VALUE;
            int cntOfPos = n / k + (n % k > i ? 1 : 0);
            for (int j = 0; j < 1 << 10; j++) {
                for (int z = i; z < n; z++) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j^nums[z]] + cntOfPos - stat[i][nums[z]]);
                }
                dp[i+1][j] = Math.min(dp[i+1][j], bestUpToLast+cntOfPos);
                bestToi = Math.min(bestToi, dp[i + 1][j]);
            }
            bestUpToLast = bestToi;

        }

        return dp[k][0];
    }
}