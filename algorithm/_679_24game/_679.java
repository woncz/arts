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

package _679_24game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by woncz on 8/24/2020.
 */
public class _679 {
    public static void main(String[] args) {
        int[] nums = new int[] {6, 6, 6, 6};

        ISolution solution = new Solution();
        System.out.println(solution.judgePoint24(nums));

        nums = new int[] {1,1,1,1};
        solution = new Solution();
        System.out.println(solution.judgePoint24(nums));
    }
}

interface ISolution {
    boolean judgePoint24(int[] nums);
}

/**
 * 不要被括号迷惑，实质是双目运算符，进行两个数字运算，找到所有可能性
 */
class Solution implements ISolution {

    boolean solved = false;

    final int TARGET = 24;

    final double EPSILON = 1e-6;

    @Override
    public boolean judgePoint24(int[] nums) {
        List<Double> candidate = new ArrayList<>();
        for (int n : nums) candidate.add((double) n);
        this._traceback(candidate);
        return solved;
    }

    void _traceback(List<Double> candidate) {
        // terminator
        if (solved) return;
        if (candidate.size() == 1) {
            if (Math.abs(candidate.get(0) - TARGET) < EPSILON) {
                solved = true;
                return;
            }
        }
        for (int i = 0; i < candidate.size(); i++) {
            for (int j = 0; j < i; j++) {
                Double o1 = candidate.get(i), o2 = candidate.get(j);
                // all possibilities
                List<Double> next = new ArrayList<>();
                next.addAll(Arrays.asList(o1 + o2, o1 * o2, o1 - o2, o2 - o1));
                if (Math.abs(o1) > EPSILON) next.add(o2 / o1);
                if (Math.abs(o2) > EPSILON) next.add(o1 / o2);

                // notice the order
                // set
                candidate.remove(i);
                candidate.remove(j);

                for (Double n : next) {
                    // set
                    candidate.add(n);
                    this._traceback(candidate);
                    // recover
                    candidate.remove(candidate.size() - 1);
                }

                // recover
                candidate.add(j, o2);
                candidate.add(i, o1);
             }
        }
    }
}

