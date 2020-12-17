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

package _714_besttimetobuyandsellstockwithtransactionfee;

/**
 * Created by woncz on 12/17/2020.
 */
public class _714 {
    public static void main(String[] args) {
        int[] prices = new int[] {1, 3, 2, 8, 4, 9};
        int fee = 2;
        ISolution solution = new Solution2();
        System.out.println(solution.maxProfit(prices, fee));
    }
}

interface ISolution {
    int maxProfit(int[] prices, int fee);
}

class Solution implements ISolution {
    @Override
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // init
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }
}


class Solution2 implements ISolution {
    @Override
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] dp = new int[2];
        // init
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int i = 1; i < n; i++) {
            int tmp = dp[0];
            dp[0] = Math.max(dp[0], dp[1] + prices[i] - fee);
            dp[1] = Math.max(dp[1], tmp - prices[i]);
        }
        return dp[0];
    }
}