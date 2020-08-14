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

package _016_3sumclosest;

import java.util.Arrays;

/**
 * Created by woncz on 8/13/2020.
 */
public class _016 {
    public static void main(String[] args) {
        int[] nums = new int[] {-1,2,1,-4};
        int target = 1;

        ISolution solution = new Solution();
        System.out.println(solution.threeSumClosest(nums, target));
    }
}

interface ISolution {
    int threeSumClosest(int[] nums, int target);
}

class Solution implements ISolution {
    @Override
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;

        Arrays.sort(nums);

        // default answer
        int closestNum = nums[0] + nums[1] + nums[2];

        // two pointer left and right
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            // terminate condition
            while (l < r) {
                int threeSum = nums[i] + nums[l] + nums[r];
                if (Math.abs(threeSum - target) < Math.abs(closestNum - target)) {
                    closestNum = threeSum;
                }
                if (threeSum > target) {
                    r--;
                } else if (threeSum < target) {
                    l++;
                } else {
                    return target;
                }
            }
        }
        return closestNum;
    }
}

