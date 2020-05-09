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

package _151_reversewordsinastring;

import java.util.Arrays;
import java.util.Collections;

public class _151 {
    public static void main(String[] args) {
        ISolution s = new Solution();
        ISolution s2 = new Solution2();
        String in = "the sky is blue";
        String res = s.reverseWords(in);

        System.out.println(res);
        System.out.println(s2.reverseWords(in));

        in = "  hello world!  ";
        res = s.reverseWords(in);
        System.out.println(res);
        System.out.println(s2.reverseWords(in));

        in = "a good   example";
        res = s.reverseWords(in);
        System.out.println(res);

        System.out.println(s2.reverseWords(in));
    }
}


interface ISolution {
    String reverseWords(String s);
}

class Solution implements ISolution {
    @Override
    public String reverseWords(String s) {
        if (s == null || "".equals(s)) return s;

        s = s.trim();

        StringBuilder sb = new StringBuilder();
        String[] ts = s.split(" ");
        for (int i = ts.length - 1; i >= 0; i--) {
            if ("".equals(ts[i])) continue;
            sb.append(ts[i]).append(" ");
        }
        return sb.toString().trim();
    }
}

class Solution2 implements ISolution {
    @Override
    public String reverseWords(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
