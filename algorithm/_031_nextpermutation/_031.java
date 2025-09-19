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

package _031_nextpermutation;

import java.util.Arrays;

/**
 * Created by woncz on 12/3/2020.
 */
public class _031 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        ISolution solution = new Solution();
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {3, 2, 1};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {1, 1, 5};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {1};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println("// solution2 ================");
        // solution2
        nums = new int[] {1, 2, 3};
        solution = new Solution2();
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {3, 2, 1};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {1, 1, 5};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {1};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}

interface ISolution {
    void nextPermutation(int[] nums);
}

class Solution implements ISolution {
    @Override
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;
        boolean changed = false;
        for (int i = n - 1; i > 0 && !changed; i--) {
            for (int j = i - 1; j >= 0 && !changed; j--) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                    changed = true;
                }
            }
        }

        if (!changed) {
            int m = n / 2;
            for (int i = 0; i < m; i++) {
                swap(nums, i, n - 1 - i);
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

class Solution2 implements ISolution {
    @Override
    public void nextPermutation(int[] nums) {
        int n = nums.length - 1;
        int i = n - 2;
        while (i >= 0 && nums[i] > nums[i + 1]) {
            i--;
        }

        if (i > 0) {
            int j = n - 1;
            while (nums[j] < nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, n - 1);
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
}