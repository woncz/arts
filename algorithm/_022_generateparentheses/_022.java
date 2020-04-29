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

package _022_generateparentheses;

import java.util.LinkedList;
import java.util.List;

public class _022 {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> list = s.generateParenthesis(3);
        System.out.println(list);
    }
}

interface ISolution {
    List<String> generateParenthesis(int n);
}

class Solution implements ISolution {

    @Override
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        this.generate(res, null, n, n);
        return res;
    }

    private void generate(List<String>res, StringBuilder sb, int left, int right) {
        // constraint
        if (left > right) {
            return;
        }

        // 1. terminator
        if (left == 0) {
            for (int i = 0; i < right; i++) {
                sb.append(")");
            }
            res.add(sb.toString());
            return;
        }

        // 2. process data
        if (sb == null) {
            generate(res, new StringBuilder("("), left - 1, right);
        } else {
            generate(res, new StringBuilder(sb.toString()).append("("), left - 1, right);
            generate(res, new StringBuilder(sb.toString()).append(")"), left, right - 1);
        }
    }
}
