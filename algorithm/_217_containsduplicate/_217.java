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

package _217_containsduplicate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by woncz on 12/14/2020.
 */
public class _217 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,1};
        ISolution solution = new Solution2();
        System.out.println(solution.containsDuplicate(nums));

        nums = new int[] {1,2,3,4};
        System.out.println(solution.containsDuplicate(nums));
    }
}

interface ISolution {
    boolean containsDuplicate(int[] nums);
}

class Solution implements ISolution {
    @Override
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> cache = new HashSet<>();
        for (int num : nums) {
            if (cache.contains(num)) return true;
            cache.add(num);
        }
        return false;
    }
}

class Solution2 implements ISolution {
    @Override
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}