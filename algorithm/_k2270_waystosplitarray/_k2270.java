/*
 * Copyright [2023]
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

package _k2270_waystosplitarray;

/**
 * Created by woncz on 1/13/2025.
 */
public class _k2270 {
    public static void main(String[] args) {
        int[] nums = new int[] {10, 4, -8, 7};
        ISolution s1 = new Solution1();
        System.out.println(s1.waysToSplitArray(nums));

        nums = new int[] {2,3,1,0};
        System.out.println(s1.waysToSplitArray(nums));
    }
}

interface ISolution {
    int waysToSplitArray(int[] nums);
}

class Solution1 implements ISolution {
    @Override
    public int waysToSplitArray(int[] nums) {
        long left = 0, right = 0;
        int ans = 0;
        for (int n : nums) {
            right += n;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            left += nums[i];
            right -= nums[i];
            if (left >= right) {
                ans++;
            }
        }
        return ans;
    }
}