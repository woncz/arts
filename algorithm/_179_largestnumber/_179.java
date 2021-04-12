/*
 * Copyright [2021]
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

package _179_largestnumber;

import java.util.Arrays;

/**
 * Created by woncz on 4/12/2021.
 */
public class _179 {
    public static void main(String[] args) {
        ISolution solution = new Solution();
        int[] nums = new int[] {3,30,34,5,9};
        System.out.println(solution.largestNumber(nums));
    }
}

interface ISolution {
    String largestNumber(int[] nums);
}

class Solution implements ISolution {
    @Override
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] t = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            t[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(t, (a, b) -> (b + a).compareTo(a + b));

        if (t[0].charAt(0) == '0') return "0";

        StringBuilder sb = new StringBuilder();
        for (String s : t) {
            sb.append(s);
        }
        return sb.toString();
    }
}
