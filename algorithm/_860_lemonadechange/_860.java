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

package _860_lemonadechange;

/**
 * Created by woncz on 12/10/2020.
 */
public class _860 {
    public static void main(String[] args) {
        int[] bills = new int[] {5,5,5,10,20};
        ISolution solution = new Solution();
        System.out.println(solution.lemonadeChange(bills));

        bills = new int[] {5,5,10,10,20};
        System.out.println(solution.lemonadeChange(bills));

        bills = new int[] {5,5,10,20,5,5,5,5,5,5,5,5,5,10,5,5,20,5,20,5};
        System.out.println(solution.lemonadeChange(bills));

    }
}

interface ISolution {
    boolean lemonadeChange(int[] bills);
}

class Solution implements ISolution {
    @Override
    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) return true;

        int five = 0, ten = 0;
        for (int bill : bills) {
            switch (bill) {
                case 5:
                    five++;
                    break;
                case 10:
                    five--;
                    ten++;
                    break;
                case 20:
                    if (ten > 0) {
                        ten--;
                        five--;
                    } else {
                        five -= 3;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("");
            }
            if (five < 0) return false;
        }
        return true;
    }
}
