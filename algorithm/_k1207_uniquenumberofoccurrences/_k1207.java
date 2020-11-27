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

package _k1207_uniquenumberofoccurrences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by woncz on 10/28/2020.
 */
public class _k1207 {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,2,1,1,3};
        ISolution solution = new Solution();
        boolean ans = solution.uniqueOccurrences(arr);
        System.out.println(ans);
    }
}

interface ISolution {
    boolean uniqueOccurrences(int[] arr);
}

class Solution implements ISolution {
    @Override
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> stat = new HashMap<>();
        for (int a : arr) {
            Integer cnt = stat.get(a);
            if (cnt == null) {
                cnt = 0;
            }
            stat.put(a, ++cnt);
        }
        Set<Integer> cnts = new HashSet<>();
        for (Integer v : stat.values()) {
            if (cnts.contains(v)) return false;
            cnts.add(v);
        }
        return true;
    }
}
