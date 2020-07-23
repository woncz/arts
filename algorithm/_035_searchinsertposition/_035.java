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

package _035_searchinsertposition;

/**
 * Created by woncz on 7/21/2020.
 */
public class _035 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        ISolution solution = new SolutionBinarySearch();
        int ans = solution.searchInsert(nums, 5);
        System.out.println(ans);

        ans = solution.searchInsert(nums, 2);
        System.out.println(ans);

        ans = solution.searchInsert(nums, 7);
        System.out.println(ans);

        ans = solution.searchInsert(nums, 0);
        System.out.println(ans);
    }
}

interface ISolution {
    int searchInsert(int[] nums, int target);
}

class Solution implements ISolution {
    @Override
    public int searchInsert(int[] nums, int target) {
        if (nums == null) return 0;
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
            if (nums[i] > target ) {
                p = i;
                break;
            }
            if (i == nums.length - 1) {
                p = nums.length;
            }
        }
        return p;
    }
}

class SolutionBinarySearch implements ISolution {
    @Override
    public int searchInsert(int[] nums, int target) {
        if (nums == null) return 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target ) return mid;
            if (nums[mid] > target) right = mid - 1;
            if (nums[mid] < target) left = mid + 1;
        }
        return left == right && nums[left] < target ? left + 1 : left;
    }
}
