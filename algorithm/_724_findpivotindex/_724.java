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

package _724_findpivotindex;

/**
 * Created by woncz on 1/28/2021.
 */
public class _724 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 7, 3, 6, 5, 6};
        ISolution solution = new Solution();
        System.out.println(solution.pivotIndex(nums));

        nums = new int[] {1, 2, 3};
        System.out.println(solution.pivotIndex(nums));

        nums = new int[] {2, 1, -1};
        System.out.println(solution.pivotIndex(nums));
    }
}

interface ISolution {
    int pivotIndex(int[] nums);
}

class Solution implements ISolution {
    @Override
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (left * 2 + nums[i] == sum) {
                return i;
            } else {
                left += nums[i];
            }
        }
        return -1;
    }
}
