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

package _424_longestrepeatingcharacterreplacement;

/**
 * Created by woncz on 2/2/2021.
 */
public class _424 {
    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;
        ISolution solution = new Solution();
        System.out.println(solution.characterReplacement(s, k));

        s = "AABABBA";
        k = 1;
        System.out.println(solution.characterReplacement(s, k));
    }
}

interface ISolution {
    int characterReplacement(String s, int k);
}

class Solution implements ISolution {
    @Override
    public int characterReplacement(String s, int k) {
        int[] stat = new int[26];
        int n = s.length();
        int maxi = 0;
        int left = 0, right = 0;
        while (right < n) {
            int i = s.charAt(right) - 'A';
            stat[i]++;
            maxi = Math.max(maxi, stat[i]);
            if (right - left + 1 - maxi > k) {
                i = s.charAt(left) - 'A';
                stat[i]--;
                left++;
            }
            right++;
        }
        return right - left;
    }
}
