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

package _763_partitionlabels;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 10/24/2020.
 */
public class _763 {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        ISolution solution = new Solution();
        List<Integer> ans = solution.partitionLabels(s);
        System.out.println(ans);
    }
}

interface ISolution {
    List<Integer> partitionLabels(String S);
}

class Solution implements ISolution {
    @Override
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.equals("")) return Collections.emptyList();

        // 预处理，记录最后一次出现位置
        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }

        // 双指针，判断尾指针是否达到最大处
        List<Integer> ans = new LinkedList<>();
        int start = 0, end = 0;
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(last[S.charAt(i) - 'a'], end);
            if (i == end) {
                ans.add(end - start + 1);
                start = end + 1;
            }
        }
        return ans;
    }
}