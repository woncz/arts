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

package _410_splitarraylargestsum;

import java.util.Arrays;

/**
 * Created by woncz on 7/27/2020.
 */
public class _410 {
    public static void main(String[] args) {
        int[] nums = new int[] {7,2,5,10,8};
        int m = 2;
        ISolution solution = new Solution();
        System.out.println(solution.splitArray(nums, m));

        solution = new SolutionBSAndGreedy();
        System.out.println(solution.splitArray(nums, m));
    }
}

interface ISolution {
    int splitArray(int[] nums, int m);
}

class Solution implements ISolution {
    @Override
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        // init dp state
        int[][] dp = new int[n + 1][m + 1];
        for (int[] a : dp) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }

        // accumulate array
        int[] acc = new int[n + 1];
        for (int i = 0; i < n; i++) {
            acc[i + 1] = acc[i] + nums[i];
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], acc[i] - acc[k]));
                }
            }
        }
        return dp[n][m];
    }
}


/**
 * binary search and greedy algos
 *
 * very smart idea
 */
class SolutionBSAndGreedy implements ISolution {
    @Override
    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
            if (left < nums[i]) {
                left = nums[i];
            }
        }

        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean check(int[] nums, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > x) {
                cnt++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return cnt <= m;
    }
}
