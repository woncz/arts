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

package _026_removeduplicatesfromsortedarray;

/**
 * Created by woncz on 8/14/2020.
 */
public class _026 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 2};
        ISolution solution = new Solution();
        System.out.println(solution.removeDuplicates(nums));

        nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        System.out.println(solution.removeDuplicates(nums));
    }
}

interface ISolution {
    int removeDuplicates(int[] nums);
}

class Solution implements ISolution {
    @Override
    public int removeDuplicates(int[] nums) {
        int cnt = 1;
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r] != nums[l]) {
                nums[++l] = nums[r];
                cnt++;
            }
            r++;
        }
        return cnt;
    }
}