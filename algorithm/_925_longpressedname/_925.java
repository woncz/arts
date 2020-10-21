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

package _925_longpressedname;

/**
 * Created by woncz on 10/21/2020.
 */
public class _925 {
    public static void main(String[] args) {
        String name = "alex";
        String typed = "aaleex";

        name = "saeed"; typed = "ssaaedd";
        name = "leelee"; typed = "lleeelee";
        name = "laiden"; typed = "laiden";
        name = "a";typed = "aaaaaaaaaaaaaaaaa";
        ISolution solution = new Solution();
        System.out.println(solution.isLongPressedName(name, typed));
    }
}

interface ISolution {
    boolean isLongPressedName(String name, String typed);
}

class Solution implements ISolution {
    @Override
    public boolean isLongPressedName(String name, String typed) {
        if (name == null || name.equals("") || typed == null || typed.equals("")) return true;

        int left = 0, right = 0;
        while (right < typed.length()) {
            if (left < name.length() && name.charAt(left) == typed.charAt(right)) {
                left++; right++;
            } else if (right > 0 && typed.charAt(right) == typed.charAt(right - 1)) {
                right++;
            } else {
                return false;
            }
        }

        return left == name.length();
    }
}