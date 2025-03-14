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

package _040_combinationsumii;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by woncz on 9/10/2020.
 */
public class _040 {
    public static void main(String[] args) {
        int[] candidates = new int[] {10,1,2,7,6,1,5};
        int target = 8;
        ISolution solution = new Solution();
        System.out.println(solution.combinationSum2(candidates, target));

        candidates = new int[] {2,5,2,1,2};
        target = 5;
        System.out.println(solution.combinationSum2(candidates, target));

        candidates = new int[] {29,19,14,33,11,5,9,23,23,33,12,9,25,25,12,21,14,11,20,30,17,19,5,6,6,5,5,11,12,25,31,28,31,33,27,7,33,31,17,13,21,24,17,12,6,16,20,16,22,5};
        target = 28;
        System.out.println(solution.combinationSum2(candidates, target));
    }
}

interface ISolution {
    List<List<Integer>> combinationSum2(int[] candidates, int target);
}

class Solution implements ISolution {
    int MIN = 0;

    Set<List<Integer>> ans = new HashSet<>();

    @Override
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        MIN = candidates[0];
        ans.clear();
        this._process(new LinkedList<>(), 0, candidates, target, 0);
        return new LinkedList<>(ans);
    }

    void _process(List<Integer> selected, int sum, int[] candidates, int target, int index) {
        // terminator
        int delta = target - sum;
        if (delta == 0) {
            ans.add(new LinkedList<>(selected));
            return;
        }
        // no gain
        if (delta < MIN) {
            return;
        }

        for (int i = index; i < candidates.length && candidates[i] <= delta; i++) {
            // select
            sum += candidates[i];
            selected.add(candidates[i]);

            // drill down
            this._process(selected, sum, candidates, target, i + 1);

            // recover
            sum -= candidates[i];
            selected.remove(selected.size() - 1);
        }
    }
}

class Solution2 implements ISolution {

    int MIN = 0;

    Set<List<Integer>> ans = new HashSet<>();

    Map<Integer, Integer> stat = new HashMap<>();

    @Override
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        stat.clear();
        for (int c : candidates) {
            Integer cnt = stat.get(c);
            if (cnt == null) {
                cnt = 0;
            }
            stat.put(c, ++cnt);
        }
        Arrays.sort(candidates);
        MIN = candidates[0];
        ans.clear();

        this._process(new LinkedList<>(), 0, candidates, target, 0);
        return new LinkedList<>(ans);
    }

    void _process(List<Integer> selected, int sum, int[] candidates, int target, int index) {
        // terminator
        int delta = target - sum;
        if (delta == 0) {
            ans.add(new LinkedList<>(selected));
            return;
        }
        // no gain
        if (delta < MIN) {
            return;
        }

        for (int i = index; i < candidates.length && candidates[i] <= delta; i++) {

            // 统一处理
            int times = stat.get(candidates[i]);
            for (int j = 0; j <= times; j++) {
                // select
                sum += candidates[i] * j;
                for (int k = 1; k <= j; k++) {
                    selected.add(candidates[i]);
                }

                // drill down
                this._process(selected, sum, candidates, target, i + times);

                // recover
                sum -= candidates[i] * j;
                for (int k = 1; k <= j; k++) {
                    selected.remove(selected.size() - 1);
                }
            }
        }
    }
}
