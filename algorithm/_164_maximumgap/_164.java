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

package _164_maximumgap;

import java.util.Arrays;

/**
 * Created by woncz on 11/26/2020.
 */
public class _164 {
    public static void main(String[] args) {
        int[] nums = new int[] {5,3,6,9,1,13};
        ISolution solution = new Solution();
        int gap = solution.maximumGap(nums);
        System.out.println(gap);

        nums = new int[] {1,1,1, 1};
        gap = solution.maximumGap(nums);
        System.out.println(gap);
    }
}

interface ISolution {
    int maximumGap(int[] nums);
}

/**
 * 桶排序，均匀分布下每个桶只有一个元素
 */
class Solution implements ISolution {
    @Override
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        int min = nums[0], max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min == max) return 0;

        int n = nums.length;
        int gap = (int) Math.ceil((max - min) * 1.0 / (n - 1));
        int[] bucketMin = new int[n];
        int[] bucketMax = new int[n];

        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        for (int num : nums) {
            // 映射之后, bucketMin和bucketMax即为排序后结果
            int i = (num - min) / gap;
            bucketMin[i] = Math.min(bucketMin[i], num);
            bucketMax[i] = Math.max(bucketMax[i], num);
        }

        int previous = min;
        for (int i = 0; i < bucketMin.length; i++) {
            if (bucketMin[i] != Integer.MAX_VALUE) {
                gap = Math.max(gap, bucketMin[i] - previous);
                previous = bucketMax[i];
            }
        }
        return gap;
    }
}