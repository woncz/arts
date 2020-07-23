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

package _312_burstballoons;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 7/19/2020.
 */
public class _312 {
    public static void main(String[] args) {
        int[] coins = new int[] {3, 1, 5, 8, 2, 4, 7, 9};

        ISolution s1 = new Solution1();
        int ans1 = s1.maxCoins(coins);
        System.out.println(ans1);

        //int[] coins = new int[] {1, 2};
        ISolution solution = new Solution();
        int ans = solution.maxCoins(coins);
        System.out.println("ans : " + ans);

        solution = new SolutionDP();
        ans = solution.maxCoins(coins);
        System.out.println("ans : " + ans);
    }
}


class Solution1 implements ISolution {
    @Override
    public int maxCoins(int[] nums) {
        // check valid
        if (nums == null) return 0;

        int length = nums.length;
        int[] numss = new int[nums.length + 2];
        for (int i = 1; i <= length; i++) {
            numss[i] = nums[i - 1];
        }
        numss[0] = numss[length + 1] = 1;

        int[][] dp = new int[length + 2][length + 2];

        for (int left = length; left >= 0; left--) {
            for (int right = left + 2; right <= length + 1; right++) {
                int max = 0;
                for (int p = left + 1; p < right; p++) {
                    int temp = dp[left][p] + numss[left] * numss[p] * numss[right] + dp[p][right];
                    if (temp > max) max = temp;
                }
                dp[left][right] = max;
            }
        }

        return dp[0][length + 1];
    }
}

interface ISolution {
    int maxCoins(int[] nums);
}

class Solution implements ISolution {
    @Override
    public int maxCoins(int[] nums) {
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            l.add(nums[i]);
        }
        return (int)_process(l);
    }

    long _process(List<Integer> nums) {
        if (nums == null | nums.size() == 0) return 0;
        int N = nums.size();
        List<Long> cnt = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            List<Integer> next = new LinkedList<>(nums);
            next.remove(i);
            cnt.add(value(i, nums) + _process(next));
        }
        long max = -1;
        int index = -1;
        for (int i = 0; i < cnt.size(); i++) {
            if (cnt.get(i) > max) {
                index = i;
                max = cnt.get(i);
            }
        }
        return cnt.get(index);
    }

    long value(int i, List<Integer> list) {
        int n1, n2, n3;
        if (i - 1 < 0) {
            n1 = 1;
        } else {
            n1 = list.get(i - 1);
        }
        n2 = list.get(i);
        if (i + 1 == list.size()) {
            n3 = 1;
        } else {
            n3 = list.get(i + 1);
        }
        return n1 * n2 * n3;
    }
}

/**
 * 有一个关键点：DP[left][i] + DP[i][right] + temp(本次分解计算因子) = DP[left][right], 这个不好理解
 */
class SolutionDP implements ISolution {
    @Override
    public int maxCoins(int[] nums) {
        if (nums == null) return 0;
        int length = nums.length;
        int[] nums2 = new int[nums.length + 2];
        System.arraycopy(nums, 0, nums2, 1, length);
        nums2[0] = nums2[length + 1] = 1;
        int[][] dp = new int[length + 2][length + 2];

        // left's range: [0, length], right's range: [left + 2, length + 1]
        for (int left = length; left >= 0; left--) {
            for (int right = left + 2; right < length + 2; right++) {
                int max = 0;
                // i's range: (left, right)
                for (int i = left + 1; i < right; i++) {
                    int temp = dp[left][i] + dp[i][right] + nums2[left] * nums2[i] * nums2[right];
                    if (temp > max) {
                        max = temp;
                    }
                }
                dp[left][right] = max;
            }
        }
        return dp[0][length + 1];
    }
}