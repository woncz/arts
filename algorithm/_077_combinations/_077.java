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

package _077_combinations;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 9/8/2020.
 */
public class _077 {
    public static void main(String[] args) {
        int n = 10, k = 3;
        ISolution solution = new Solution();
        System.out.println(solution.combine(n, k));
        System.out.println(solution.combine(n, k).size());
    }
}

interface ISolution {
    List<List<Integer>> combine(int n, int k);
}

class Solution implements ISolution {

    List<List<Integer>> ans = new LinkedList<>();

    int N, K;

    @Override
    public List<List<Integer>> combine(int n, int k) {
        N = n;
        K = k;
        ans.clear();
        this._process(1, new LinkedList<>());
        return ans;
    }

    void _process(int n, List<Integer> selected) {
        // 在叶子的末端进行数据保存，这样就可以共享同一个中间过程数据
        if (selected.size() == K) {
            ans.add(new LinkedList<>(selected));
            return;
        }
        // fast-fail
        if (n > N || selected.size() + (N - n + 1) < K) {
            return;
        }

        // select current
        selected.add(n);
        this._process(n + 1, selected);
        selected.remove(selected.size() - 1);

        this._process(n + 1, selected);
    }
}
