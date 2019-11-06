/*
 * Copyright [2017]
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

package _033_searchinrotatedsortedarray;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[] {4,5,6,7,0,1,2};
        ISolution s2 = new Solution2();
        int index = s2.search(nums, 4);
        System.out.println(index);

        nums = new int[] {4,5,6,7,0,1,2};
        index = s2.search(nums, 3);
        System.out.println(index);
    }
}

interface ISolution {
    int search(int[] nums, int target);
}

/**
 * 暴力法，顺序查找
 * time complexity : O(N)
 */
class Solution1 implements ISolution {
    @Override
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) return i;
        }
        return -1;
    }
}

/**
 * 二分法 直接判断
 */
class Solution2 implements ISolution {
    @Override
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 判断断层位于哪一侧
            if (nums[left] <= nums[mid]) { // 左侧正常，右侧断层
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 左侧断层，右侧正常
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }
}

/**
 * 二分法 先找二分查找最小值，再判断
 */
class Solution3 implements ISolution {
    @Override
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;

        // 找出旋转处 refer #153
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }

        int rotate = left;
        left = 0; right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int realmid = (mid + rotate) % nums.length;
            if (nums[realmid] == target) return realmid;
            if (nums[realmid] < target) left = mid + 1;
            if (nums[realmid] > target) right = mid - 1;
        }
        return -1;
    }
}

