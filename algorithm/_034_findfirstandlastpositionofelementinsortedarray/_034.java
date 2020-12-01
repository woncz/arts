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

package _034_findfirstandlastpositionofelementinsortedarray;

import java.util.Arrays;

/**
 * Created by woncz on 12/1/2020.
 */
public class _034 {
    public static void main(String[] args) {
        int[] nums = new int[] {5,7,7,8,8,10};
        int target = 8;

        ISolution solution = new Solution();
        System.out.println(Arrays.toString(solution.searchRange(nums, target)));

    }
}

interface ISolution {
    int[] searchRange(int[] nums, int target);
}

class Solution implements ISolution {

    @Override
    public int[] searchRange(int[] nums, int target) {
        int start = firstGreaterEqual(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[] {-1, -1};
        }
        return new int[] {start, firstGreaterEqual(nums, target + 1) - 1};
    }

    int firstGreaterEqual(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
