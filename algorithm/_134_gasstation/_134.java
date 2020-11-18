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

package _134_gasstation;

/**
 * Created by woncz on 11/18/2020.
 */
public class _134 {
    public static void main(String[] args) {
        int[] gas = new int[] {1,2,3,4,5};
        int[] cost = new int[] {3,4,5,1,2};

        ISolution solution = new Solution2();
        int start = solution.canCompleteCircuit(gas, cost);
        System.out.println(start);

        gas = new int[] {2,3,4};
        cost = new int[] {3,4,4};
        start = solution.canCompleteCircuit(gas, cost);
        System.out.println(start);
    }
}

interface ISolution {
    int canCompleteCircuit(int[] gas, int[] cost);
}

class Solution implements ISolution {
    @Override
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int s1 = 0;
        for (int g : gas) {
            s1 += g;
        }
        int s2 = 0;
        for (int c : cost) {
            s2 += c;
        }
        if (s1 < s2) return -1;

        for (int i = 0; i < gas.length; i++) {
            if (gas[i] < cost[i]) {
                continue;
            }
            if (start(gas, cost, i)) {
                return i;
            }
        }
                
        return -1;
    }

    boolean start(int[] gas, int[] cost, int index) {
        int g = 0;
        for (int i = 0; i < gas.length; i++) {
            // 环形指针
            int j = (index + i) % gas.length;

            g += gas[j] - cost[j];
            if (g < 0) return false;
        }
        return true;
    }
}

class Solution2 implements ISolution {
    @Override
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int diff = 0;
        int minDiff = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            diff += gas[i] - cost[i];
            if (diff < minDiff) {
                minDiff = diff;
                minIndex = i;
            }
        }
        return diff < 0 ? -1 : (minIndex + 1) % n;
    }
}