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

package _696_countbinarysubstrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woncz on 8/10/2020.
 */
public class _696 {
    public static void main(String[] args) {
        String s = "00110011";
        ISolution solution = new Solution2();
        System.out.println(solution.countBinarySubstrings(s));

        s = "10101";
        System.out.println(solution.countBinarySubstrings(s));
    }
}

interface ISolution {
    int countBinarySubstrings(String s);
}

/**
 * time complexity O(N^2)
 */
class Solution implements ISolution {
    @Override
    public int countBinarySubstrings(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            int cc = 0;
            boolean flag = true;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (!flag) break; // 无法回头
                    cc++;
                } else {
                    flag = false;
                    cc--;
                }
                if (cc == 0) { // 达标则退出继续
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
}

class Solution2 implements ISolution {
    @Override
    public int countBinarySubstrings(String s) {
        List<Integer> stats = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int cnt = 0;
            int c = s.charAt(i);
            while (i < s.length() && s.charAt(i) == c) {
                i++;
                cnt++;
            }
            stats.add(cnt);
        }

        int ans = 0;
        for (i = 1; i < stats.size(); i++) {
            ans += Math.min(stats.get(i - 1), stats.get(i));
        }
        return ans;
    }
}

class Solution3 implements ISolution {
    @Override
    public int countBinarySubstrings(String s) {
        int cur = 1, pre = 0, ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cur++;
            } else {
                ans += Math.min(pre, cur);
                pre = cur;
                cur = 1;
            }
        }
        return ans + Math.min(pre, cur);
    }
}