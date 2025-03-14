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

package _039_combinationsum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 9/9/2020.
 */
public class _039 {
    public static void main(String[] args) {
        int[] candidates = new int[] {2,3,6,7};
        int target = 7;

        ISolution solution = new Solution();
        System.out.println(solution.combinationSum(candidates, target));

        candidates = new int[] {2,3,5};
        target = 8;
        System.out.println(solution.combinationSum(candidates, target));
    }
}

interface ISolution {
    List<List<Integer>> combinationSum(int[] candidates, int target);
}

class Solution implements ISolution {

    List<List<Integer>> ans = new LinkedList<>();

    int MIN;

    @Override
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        MIN = candidates[0];
        ans.clear();
        this._process(new LinkedList<>(), 0, candidates, target);
        return ans;
    }

    void _process(List<Integer> selected, int sum, int[] candidates, int target) {
        // terminator
        int delta = target - sum;
        if (delta == 0) {
            ans.add(new LinkedList<>(selected));
            return;
        }
        if (delta < MIN) {
            return;
        }

        for (int i = 0; i < candidates.length && candidates[i] <= delta; i++) {
            // filter repeat result
            int last = selected.size() == 0 ? Integer.MAX_VALUE : selected.get(selected.size() - 1);
            if (last < candidates[i]) {
                continue;
            }

            sum += candidates[i];
            selected.add(candidates[i]);

            this._process(selected, sum, candidates, target);

            // recover
            sum -= candidates[i];
            selected.remove(selected.size() - 1);
        }
    }
}