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

package _830_positionsoflargegroups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by woncz on 1/5/2021.
 */
public class _830 {
    public static void main(String[] args) {
        String s = "abbxxxxzzy";
        ISolution solution = new Solution();
        print(solution.largeGroupPositions(s));

        s = "abc";
        print(solution.largeGroupPositions(s));

        s = "abcdddeeeeaabbbcd";
        print(solution.largeGroupPositions(s));
    }

    static void print(List<List<Integer>> ans) {
        ans.forEach(a -> {
            System.out.print(a);
        });
        System.out.println();
    }
}

interface  ISolution {
    List<List<Integer>> largeGroupPositions(String s);
}

class Solution implements ISolution {
    @Override
    public List<List<Integer>> largeGroupPositions(String s) {
        if (s == null || s.length() < 3) return Collections.emptyList();

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0, j = 0; i < s.length(); i = j) {
            while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                j++;
            }
            if (j - i >= 3) {
                ans.add(Arrays.asList(i, j - 1));
            }
        }
        return ans;
    }
}
