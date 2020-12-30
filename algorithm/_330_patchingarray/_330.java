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

package _330_patchingarray;

/**
 * Created by woncz on 12/29/2020.
 */
public class _330 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 4, 13, 43};
        int n = 100;
        ISolution solution = new Solution();
        System.out.println(solution.minPatches(nums, n));
    }
}

interface ISolution {
    int minPatches(int[] nums, int n);
}

class Solution implements ISolution {
    @Override
    public int minPatches(int[] nums, int n) {
        long miss = 1;
        int added = 0, i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                added++;
            }
        }
        return added;
    }
}
