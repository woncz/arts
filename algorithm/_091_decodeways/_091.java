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

package _091_decodeways;

public class _091 {
    public static void main(String[] args) {
        ISolution s1 = new Solution();
        String s = "12";
        System.out.println(s1.numDecodings(s));

        s = "226";
        System.out.println(s1.numDecodings(s));
    }
}

interface ISolution {
    int numDecodings(String s);
}

class Solution implements ISolution {
    @Override
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.startsWith("0")) return 0;

        if (s.length() == 1) return 1;

        // define dp state
        int dp[] = new int[s.length() + 1];

        // init
        if (s.charAt(0) != '0') dp[0] = 1;
        if (s.charAt(1) != '0') dp[1] = 1;

        // induct dp formula
        for (int i = 2; i < s.length() + 1; i++) {
            int t1 = Integer.parseInt(s.substring(i - 1, i));
            if (1 <= t1 && t1 <= 9) dp[i] += dp[i - 1];

            int t2 = Integer.parseInt(s.substring(i - 2, i));
            if (10 <= t2 && t2 <= 26) dp[i] += dp[i - 2];
        }
        return dp[s.length()];
    }
}
