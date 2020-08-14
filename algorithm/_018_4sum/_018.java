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

package _018_4sum;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 8/14/2020.
 */
public class _018 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 0, -1, 0, 2, -2, 0, -1, 0, -2, 2};
        int target = 0;
        ISolution solution = new Solution();
        List<List<Integer>> ans = solution.fourSum(nums,target);
        System.out.println(ans);
    }
}

interface ISolution {
    List<List<Integer>> fourSum(int[] nums, int target);
}

class Solution implements ISolution {

    @Override
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) return Collections.emptyList();

        Arrays.sort(nums);

        List<List<Integer>> ret = new LinkedList<>();

        for (int i = 0; i < nums.length - 3; ) {
            for (int j = i + 1; j < nums.length - 2; ) {
                int l = j + 1, r = nums.length - 1;
                while (l < r) {
                    int t = nums[i] + nums[j] + nums[l] + nums[r];
                    if (t > target) {
                        r--;
                    } else if (t < target) {
                        l++;
                    } else {
                        ret.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++; r--;

                        // avoid repeat result
                        while (l < r && nums[l] == nums[l - 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r + 1]) {
                            r--;
                        }
                    }
                }
                j++;
                while (j < nums.length - 2 && nums[j] == nums[j - 1]) {
                    j++;
                }
            }
            i++;
            while (i < nums.length - 3 && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return ret;
    }
}
