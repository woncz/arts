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

package _047_permutationsii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by woncz on 9/18/2020.
 */
public class _047 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 1, 1, 2};
        ISolution solution = new Solution();
        List<List<Integer>> ret = solution.permuteUnique(nums);
        for (List<Integer> r : ret) {
            System.out.println(r);
        }

        System.out.println("=====================");

        solution = new Solution2();
        ret = solution.permuteUnique(nums);
        for (List<Integer> r : ret) {
            System.out.println(r);
        }
    }
}

interface ISolution {
    List<List<Integer>> permuteUnique(int[] nums);
}

class Solution implements ISolution {
    @Override
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        this.backtrack(ans, new LinkedList<>(), new HashSet<>(), nums);
        return new ArrayList<>(ans);
    }

    void backtrack(Set<List<Integer>> ans, List<Integer> selected, Set<Integer> index, int[] nums) {
        if (selected.size() == nums.length) {
            ans.add(new LinkedList<>(selected));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (index.contains(i)) continue;
                selected.add(nums[i]);
                index.add(i);
                this.backtrack(ans, selected, index, nums);
                // recover
                selected.remove(selected.size() - 1);
                index.remove(i);
            }
        }
    }
}

class Solution2 implements ISolution {

    boolean[] visited;

    @Override
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        this.backtrack(ans, new LinkedList<>(), 0, nums);
        return ans;
    }

    void backtrack(List<List<Integer>> ans, List<Integer> selected, int index, int[] nums) {
        if (index == nums.length) {
            ans.add(new LinkedList<>(selected));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                    continue;
                }
                visited[i] = true;
                selected.add(nums[i]);
                this.backtrack(ans, selected, index + 1, nums);
                visited[i] = false;
                selected.remove(index);
            }
        }
    }
}