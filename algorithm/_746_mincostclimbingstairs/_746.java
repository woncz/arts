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

package _746_mincostclimbingstairs;

import java.util.Arrays;

/**
 * 关键是DP状态定义
 * Created by woncz on 12/21/2020.
 */
public class _746 {
    public static void main(String[] args) {
        int[] cost = new int[] {10, 15, 20};
        ISolution solution = new Solution();
        System.out.println(solution.minCostClimbingStairs(cost));

        cost = new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(solution.minCostClimbingStairs(cost));
    }
}

interface ISolution {
    int minCostClimbingStairs(int[] cost);
}

class Solution implements ISolution {
    @Override
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];

        // init
        dp[0] = 0; dp[1] = cost[1];

        // formula
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        int min = dp[n];

        // init
        Arrays.fill(dp, 0);
        dp[0] = 1_000; dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return Math.min(min, dp[n]);
    }
}