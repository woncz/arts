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

package _842_splitarrayintofibonaccisequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woncz on 12/8/2020.
 */
public class _842 {
    public static void main(String[] args) {
        String s= "123456579";
        ISolution solution = new Solution();
        List<Integer> ans = solution.splitIntoFibonacci(s);
        System.out.println(ans);
    }
}

interface ISolution {
    List<Integer> splitIntoFibonacci(String S);
}

class Solution implements ISolution {

    @Override
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();

        fab(S, ans, 0);

        return ans;
    }

    boolean fab(String s, List<Integer> ans, int idx) {
        // terminator
        if (idx == s.length() && ans.size() >= 3) {
            return true;
        }

        for (int i = idx; i < s.length(); i++) {
            if (s.charAt(idx) == '0' && i > idx) {
                break;
            }
            Long next = Long.parseLong(s.substring(idx, i + 1));
            if (next > Integer.MAX_VALUE) break;

            int size = ans.size();
            if (size >= 2 && next > ans.get(size - 1) + ans.get(size - 2)) {
                break;
            }

            if (size < 2 || next == ans.get(size - 1) + ans.get(size - 2)) {
                ans.add(next.intValue());
                if (fab(s, ans, i + 1)) {
                    return true;
                }
                ans.remove(ans.size() - 1);
            }
        }

        return false;
    }
}
