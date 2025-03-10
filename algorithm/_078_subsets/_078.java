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

package _078_subsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 9/20/2020.
 */
public class _078 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 4};
        ISolution solution = new Solution();
        List<List<Integer>> ret = solution.subsets(nums);
        for (List<Integer> r : ret) {
            System.out.println(r);
        }

        System.out.println("==================");

        solution = new Solution2();
        ret = solution.subsets(nums);
        for (List<Integer> r : ret) {
            System.out.println(r);
        }

        System.out.println("==================");

        solution = new Solution3();
        ret = solution.subsets(nums);
        for (List<Integer> r : ret) {
            System.out.println(r);
        }

        System.out.println("Solution4:");
        solution = new Solution4();
        ret = solution.subsets(nums);
        for (List<Integer> r : ret) {
            System.out.println(r);
        }

        System.out.println("Solution5:");
        solution = new Solution5();
        ret = solution.subsets(nums);
        for (List<Integer> r : ret) {
            System.out.println(r);
        }

        int n = 4;
        System.out.println(1 << n);
    }
}

interface ISolution {
    List<List<Integer>> subsets(int[] nums);
}


class Solution implements ISolution {

    List<List<Integer>> ans = new LinkedList<>();

    @Override
    public List<List<Integer>> subsets(int[] nums) {
        ans.clear();
        ans.add(Collections.emptyList());
        for (int i = 1; i <= nums.length; i++) {
            this._process(new LinkedList<>(), i, nums, 0);
        }
        return ans;
    }

    void _process(List<Integer> selected, int cnt, int[] nums, int index) {
        // terminator
        if (selected.size() > cnt) {
            return;
        } else if (selected.size() == cnt) {
            ans.add(new LinkedList<>(selected));
            return;
        }

        // drill down
        for (int i = index; i < nums.length; i++) {
            selected.add(nums[i]);
            this._process(selected, cnt, nums, i + 1);
            // recover
            selected.remove(selected.size() - 1);
        }
    }
}

class Solution2 implements ISolution {
    List<List<Integer>> ans;
    @Override
    public List<List<Integer>> subsets(int[] nums) {
        ans = new LinkedList<>();
        this._process(new LinkedList<>(), nums, 0);
        return ans;
    }

    void _process(List<Integer> selected, int[] nums, int index) {
        // terminator
        if (index == nums.length) return;

        for (int i = index; i < nums.length; i++) {
            selected.add(nums[i]);
            ans.add(new LinkedList<>(selected));
            this._process(selected, nums, index + 1);
            selected.remove(selected.size() - 1);
        }
    }
}

class Solution3 implements ISolution {

    List<Integer> selected = new ArrayList<>();

    List<List<Integer>> ans = new ArrayList<>();

    @Override
    public List<List<Integer>> subsets(int[] nums) {
        this._process(nums, 0);
        return ans;
    }

    void _process(int[] nums, int cnt) {
        if (nums.length == cnt) {
            ans.add(new ArrayList<>(selected));
            return;
        }

        selected.add(nums[cnt]);
        this._process(nums, cnt + 1);
        selected.remove(selected.size() - 1);
        this._process(nums, cnt + 1);
    }
}

/**
 * 从输入的视角 -> 灵茶山艾府
 */
class Solution4 implements ISolution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private int[] nums;

    @Override
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        dfs(0);
        return ans;
    }

    void dfs(int i) {
        // terminator
        if (i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        //drill down
        dfs(i + 1); // not select

        path.add(nums[i]); // select
        dfs(i + 1);
        path.remove(path.size() - 1); // recover
    }
}

// 从答案的视角 -> 灵茶山艾府
class Solution5 implements ISolution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private int[] nums;

    @Override
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        dfs(0);
        return ans;
    }

    void dfs(int i) {
        ans.add(new ArrayList<>(path));
        for (int j = i; j < nums.length; j++) {
            path.add(nums[j]);
            dfs(j + 1);
            path.remove(path.size() - 1);
        }
    }
}