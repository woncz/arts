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

package _093_restoreipaddresses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 8/9/2020.
 */
public class _093 {
    public static void main(String[] args) {
        String s = "25525511135";
        ISolution solution = new Solution2();
        System.out.println(solution.restoreIpAddresses(s));

        s ="0000";
        System.out.println(solution.restoreIpAddresses(s));

        s = "010010";
        System.out.println(solution.restoreIpAddresses(s));

        s = "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        System.out.println(solution.restoreIpAddresses(s));
    }
}

interface ISolution {
    List<String> restoreIpAddresses(String s);
}

class Solution implements ISolution {
    @Override
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new LinkedList<>();
        _proc(ret, s, null, 0, 1);
        return ret;
    }

    void _proc(List<String> ret, String s, StringBuilder ans, int i, int cnt) {
        // terminator
        if (cnt == 5) return;
        if (ans == null) ans = new StringBuilder();

        for (int j = 1; j <= 3; j++) {
            // key point : the independence of variable
            if (i + j > s.length()) break;
            String cur = s.substring(i, i + j);
            StringBuilder sb = new StringBuilder(ans);
            if (validate(cur, cnt, s.substring(i + j))) {
                sb.append(cur);
                if (cnt < 4) {
                    sb.append(".");
                }
                if (cnt == 4) {
                    ret.add(sb.toString());
                }
                // drill down
                _proc(ret, s, sb, i + j, cnt + 1);
            }
        }
    }

    boolean validate(String cur, int cnt, String remain) {
        if (cur == null || cur.equals("")) return false;

        if (!cur.equals("0") && cur.startsWith("0")) return false;

        if (cur.length() < 3 || cur.compareTo("255") <= 0) {
            if (cnt == 4 && (remain != null && !remain.equals(""))) return false;
            if (cnt == 4 && (remain == null || remain.equals(""))) return true;

            String min = "";
            String max = "";
            for (int i = cnt; i < 4; i++) {
                min += "0";
                max += "255";
            }

            if (min == "") min = "0";
            if (max == "") max = "0";

            if (cnt < 4 && (remain == null || remain.equals(""))) return false;
            if (remain.length() > 3 * (4 - cnt)) return false;

            if (Long.parseLong(min) <= Long.parseLong(remain) && Long.parseLong(max) >= Long.parseLong(remain)) {
                return true;
            }
        }
        return false;
    }
}

class Solution2 implements ISolution {
    @Override
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        int len = s.length();
        for (int i = 0; i < 4 && i < len - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s.substring(j, k), s4 = s.substring(k);
                    if (validate(s1) && validate(s2) && validate(s3) && validate(s4)) {
                        ret.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return ret;
    }

    boolean validate(String s) {
        if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }
}
