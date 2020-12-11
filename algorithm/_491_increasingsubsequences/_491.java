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

package _491_increasingsubsequences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by woncz on 8/25/2020.
 */
public class _491 {
    public static void main(String[] args) {
        int[] nums = new int[] {4,6,7,7};
        ISolution solution = new Solution3();
        System.out.println(solution.findSubsequences(nums));

        nums = new int[] {4,6,7};
        solution = new Solution3();
        System.out.println(solution.findSubsequences(nums));

        nums = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        solution = new Solution3();
        System.out.println(solution.findSubsequences(nums));
    }
}

interface ISolution {
    List<List<Integer>> findSubsequences(int[] nums);
}

class Solution implements ISolution {

    List<List<Integer>> ans = new LinkedList<>();

    @Override
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length < 2) return null;

        List<Integer> candidate = new ArrayList<>();
        for (int n : nums) candidate.add(n);
        this._dfs(0, ans, candidate);

        // remove repeat
        List<List<Integer>> ret = new LinkedList<>();
        for (int i = ans.size() - 1; i >= 0; i--) {
            List<Integer> l = ans.get(i);
            if (l.size() < 2) {
                ans.remove(i);
            } else {
                if (!ret.contains(l)) {
                    ret.add(l);
                }
            }
        }

        return ret;
    }

    void _dfs(int index, List<List<Integer>> prefix, List<Integer> candidate) {
        // terminator
        if (index == candidate.size()) return;

        for (int i = prefix.size() - 1; i >= 0; i--) {
            List<Integer> p = prefix.get(i);
            int last = p.get(p.size() - 1);
            if (last <= candidate.get(index)) {
                List<Integer> np = new LinkedList<>();
                np.addAll(p);
                np.add(candidate.get(index));
                prefix.add(np);
            }
        }
        List<Integer> single = new LinkedList<>();
        single.add(candidate.get(index));
        prefix.add(single);

        // drill down
        this._dfs(++index, prefix, candidate);
    }
}

class Solution2 implements ISolution {
    @Override
    public List<List<Integer>> findSubsequences(int[] nums) {
        LinkedList<Integer> holder = new LinkedList<>();
        Set<List<Integer>> ans = new HashSet<>();

        this._dfs(ans, holder, 0, nums);

        return new LinkedList<>(ans);
    }

    void _dfs(Set<List<Integer>> satisfied, LinkedList<Integer> holder, int index, int[] nums) {
        if (holder.size() >= 2) {
            satisfied.add(new LinkedList<>(holder));
        }
        for (int i = index; i < nums.length; i++) {
            if (holder.size() == 0 || holder.peekLast() <= nums[i]) {
                holder.add(nums[i]);
                this._dfs(satisfied, holder, i + 1, nums);
                holder.remove(holder.size() - 1);
            }
        }
    }
}

class Solution3 implements ISolution {
    @Override
    public List<List<Integer>> findSubsequences(int[] nums) {
        LinkedList<Integer> holder = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        this._dfs(ans, holder, 0, nums);
        return ans;
    }

    void _dfs(List<List<Integer>> ans, LinkedList<Integer> holder, int index, int[] nums) {
        if (holder.size() > 1) ans.add(new LinkedList<>(holder));

        Set<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (used.contains(nums[i])) continue;
            if (holder.size() == 0 || holder.peekLast() <= nums[i]) {
                used.add(nums[i]);
                holder.add(nums[i]);
                this._dfs(ans, holder, i + 1, nums);
                holder.remove(holder.size() - 1);
            }
        }
    }


}

