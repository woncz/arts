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

package _154_findminimuminrotatedsortedarrayii;

/**
 * Created by woncz on 4/9/2021.
 */
public class _154 {
    public static void main(String[] args) {
        int[] nums = {1, 2};
        ISolution solution = new Solution();
        System.out.println(solution.findMin(nums));
    }
}

interface ISolution {
    int findMin(int[] nums);
}

class Solution implements ISolution {
    @Override
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right -= 1;
            }
        }
        return nums[left];
    }
}
