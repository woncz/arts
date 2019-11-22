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

package _122_besttimetobuyandsellstockii;

public class _122 {
    public static void main(String[] args) {

    }
}

interface ISolution {
    int maxProfit(int[] prices);
}

/**
 * 贪心算法
 */
class Solution1 implements ISolution {
    @Override
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int delta = prices[i + 1] - prices[i];
            if (delta > 0) {
                max += delta;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = new int[] {7,1,5,3,6,4};
        Solution1 s1 = new Solution1();
        System.out.println(s1.maxProfit(prices));

        prices = new int[] {1,2,3,4,5};
        System.out.println(s1.maxProfit(prices));

        prices = new int[] {7,6,4,3,1};
        System.out.println(s1.maxProfit(prices));
    }
}

