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

package _049_groupanagrams;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by woncz on 12/14/2020.
 */
public class _049 {
    public static void main(String[] args) {
        String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        ISolution solution = new Solution();
        System.out.println(solution.groupAnagrams(strs));
    }
}

interface ISolution {
    List<List<String>> groupAnagrams(String[] strs);
}

class Solution implements ISolution {
    @Override
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return Collections.emptyList();

        Map<String, List<String>> stat = new HashMap<>();
        for (String str : strs) {
            char[] comp = new char[26];
            for (char c : str.toCharArray()) {
                comp[c - 'a']++;
            }

            String key = String.valueOf(comp);
            List<String> value = stat.getOrDefault(key, new LinkedList<>());
            value.add(str);
            stat.put(key, value);
        }

        return new LinkedList<>(stat.values());
    }

}
