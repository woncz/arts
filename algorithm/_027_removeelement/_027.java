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

package _027_removeelement;

/**
 * Created by woncz on 8/14/2020.
 */
public class _027 {
    public static void main(String[] args) {
        int[] nums = new int[] {3,2,2,3};
        int val = 3;
        ISolution solution = new Solution();
        System.out.println(solution.removeElement(nums, val));

        nums = new int[] {0,1,2,2,3,0,4,2};
        val = 2;
        System.out.println(solution.removeElement(nums, val));
    }
}

interface ISolution {
    int removeElement(int[] nums, int val);
}

class Solution implements ISolution {
    @Override
    public int removeElement(int[] nums, int val) {
        int cnt = 0;
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r] != val) {
                nums[l++] = nums[r];
                cnt++;
            }
            r++;
        }
        return cnt;
    }
}