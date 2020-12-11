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

package _216_combinationsumiii;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 9/11/2020.
 */
public class _216 {
    public static void main(String[] args) {
        int k = 3;
        int n = 7;
        ISolution solution = new Solution();
        System.out.println(solution.combinationSum3(k, n));

        k = 3;
        n = 9;
        System.out.println(solution.combinationSum3(k, n));

        k = 4;
        n = 10;
        System.out.println(solution.combinationSum3(k, n));
    }
}

interface ISolution {
    List<List<Integer>> combinationSum3(int k, int n);
}

class Solution implements ISolution {

    List<List<Integer>> ans = new LinkedList<>();

    @Override
    public List<List<Integer>> combinationSum3(int k, int n) {
        ans.clear();
        this._process(new LinkedList<>(), 0, k, n);
        return ans;
    }

    void _process(List<Integer> selected, int sum, int k, int n) {
        // terminator
        if (selected.size() == k && sum == n) {
            ans.add(new LinkedList<>(selected));
            return;
        }
        if (selected.size() > k || sum > n) {
            return;
        }

        int last = selected.size() == 0 ? 0 : selected.get(selected.size() - 1);
        for (int i = last + 1; i <= 9; i++) {
            // select
            sum += i;
            selected.add(i);

            this._process(selected, sum, k, n);

            // recover
            sum -= i;
            selected.remove(selected.size() - 1);
        }
    }
}
