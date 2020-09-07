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

package _347_topkfrequentelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by woncz on 9/7/2020.
 */
public class _347 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1,2,2,3};
        int k = 2;

        ISolution solution = new Solution();
        System.out.println(Arrays.toString(solution.topKFrequent(nums, k)));

        nums = new int[] {1};
        k = 1;
        System.out.println(Arrays.toString(solution.topKFrequent(nums, k)));

        nums = new int[] {1,2};
        k = 2;
        System.out.println(Arrays.toString(solution.topKFrequent(nums, k)));
    }
}

interface ISolution {
    int[] topKFrequent(int[] nums, int k);
}


class Solution implements ISolution {
    @Override
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> stat = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer key = nums[i];
            Integer cnt = stat.get(key);
            if (cnt == null) {
                stat.put(key, 1);
            } else {
                stat.put(key, cnt + 1);
            }
        }

        List<Integer> count = new ArrayList<>(stat.values());
        Collections.sort(count, Comparator.reverseOrder());

        int threshold = count.get(k - 1);

        int[] ans = new int[k];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : stat.entrySet()) {
            int K = e.getKey();
            int V = e.getValue();

            if (V >= threshold) {
                ans[i++] = K;
            }
        } 

        return ans;
    }
}
