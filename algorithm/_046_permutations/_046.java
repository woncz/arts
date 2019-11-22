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

package _046_permutations;

import java.util.ArrayList;
import java.util.List;

public class _046 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        ISolution s1 = new Solution1();
        List<List<Integer>> l = s1.permute(nums);
        for (List<Integer> ll : l) {
            System.out.println(ll);
        }

        // duplicate items, the algorithm cannot work!
        nums = new int[] {1, 1, 2};
        l = s1.permute(nums);
        for (List<Integer> ll : l) {
            System.out.println(ll);
        }

    }
}

interface ISolution {
    List<List<Integer>> permute(int[] nums);
}

/**
 * 回溯算法
 */
class Solution1 implements ISolution {

    @Override
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList(), nums);
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (list.contains(nums[i])) continue; // already contains current data
                list.add(nums[i]);
                backtrack(ans, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }
}

/**
 *
 */
class Solution2 implements ISolution {
    @Override
    public List<List<Integer>> permute(int[] nums) {
        return null;
    }
}
