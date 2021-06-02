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

package _523_continuoussubarraysum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by woncz on 6/2/2021.
 */
public class _523 {
    public static void main(String[] args) {
        int[] nums = {23,2,4,6,7};
        int k = 6;

        ISolution solution = new Solution2();
        System.out.println(solution.checkSubarraySum(nums, k));

        nums = new int[] {23,2,6,4,7};
        k = 6;
        System.out.println(solution.checkSubarraySum(nums, k));

        nums = new int[] {23,2,6,4,7};
        k = 13;
        System.out.println(solution.checkSubarraySum(nums, k));

        nums = new int[] {5, 0, 0, 0};
        k = 3;
        System.out.println(solution.checkSubarraySum(nums, k));

        nums = new int[] {1, 2, 12};
        k = 6;
        System.out.println(solution.checkSubarraySum(nums, k));
    }

}

interface ISolution {
    boolean checkSubarraySum(int[] nums, int k);
}

class Solution implements ISolution {
    @Override
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) return false;

        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, - 1);

        int reminder = 0;
        for (int i = 0; i < n; i++) {
            reminder = (reminder + nums[i]) % k;
            if (prefixSum.containsKey(reminder)) {
                int prefixIndex = prefixSum.get(reminder);
                if (i - prefixIndex >= 2) return true;
            } else {
                prefixSum.put(reminder, i);
            }
        }

        return false;
    }
}

class Solution2 implements ISolution {
    @Override
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) return false;

        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        // TODO the array index chaos
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int delta = prefixSum[j];
                if (i > 0) {
                    delta = prefixSum[j] - prefixSum[i];
                }
                if (delta % k == 0) return true;
            }
        }

        return false;
    }
}
