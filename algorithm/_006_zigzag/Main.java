/*
 * Copyright [2017]
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

package _006_zigzag;

import java.util.stream.IntStream;

/**
 * 6. ZigZag Conversion
 *
 * @author woncz
 * @date 8/27/2019
 */
public class Main {

    public static void main(String[] args) {
        String ts = "HelloWorld";
        Solution s = new Solution();
        System.out.println(s.convert(ts, 4));

        ts = "PAYPALISHIRING";
        System.out.println(s.convert(ts, 3));
        System.out.println(s.convert(ts, 4));

        ts = "A";

        System.out.println(s.convert(ts, 2));

        ts = "AB";
        System.out.println(s.convert(ts, 2));
    }
}

class Solution {
    public String convert(String s, int numRows) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        if (numRows <= 1) {
            return s;
        }

        // init appender
        StringBuilder[] sbs = new StringBuilder[numRows];
        IntStream.range(0, numRows).forEach(i -> sbs[i] = new StringBuilder());

        int len = s.length();
        int N = numRows;

        for (int i = 0; i < len; i++) {
            int remainder = i % ( 2 * N - 2);
            int idx = remainder < N ? remainder : 2 * N - 2 - remainder;
            sbs[idx].append(s.charAt(i));
        }
        IntStream.range(1, numRows).forEach(i -> sbs[0].append(sbs[i]));
        return sbs[0].toString();
    }
}
