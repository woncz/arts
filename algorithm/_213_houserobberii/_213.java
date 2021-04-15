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

package _213_houserobberii;

/**
 * Created by woncz on 4/15/2021.
 */
public class _213 {
    public static void main(String[] args) {
        int[] nums = new int[] {2, 3, 2};
        ISolution solution = new Solution();
        System.out.println(solution.rob(nums));
    }

}

interface ISolution {
    int rob(int[] nums);
}

class Solution implements ISolution {
    @Override
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        // simplify
        return Math.max(_rob(nums, 0, nums.length - 2), _rob(nums, 1, nums.length - 1));
    }

    private int _rob(int[] nums, int lo, int hi) {
        // space compress
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = i + nums[j];
            exclude = Math.max(i, e);
        }
        return Math.max(include, exclude);
    }
}
