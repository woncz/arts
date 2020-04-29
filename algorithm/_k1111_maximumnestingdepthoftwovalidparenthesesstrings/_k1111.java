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

package _k1111_maximumnestingdepthoftwovalidparenthesesstrings;

public class _k1111 {
    public static void main(String[] args) {
        String seq = "(()())";
        ISolution s = new Solution();
        for (int i : s.maxDepthAfterSplit(seq)) {
            System.out.printf(i + ",");
        }
        System.out.println();

        seq = "()(())()";
        for (int i : s.maxDepthAfterSplit(seq)) {
            System.out.printf(i + ",");
        }
        System.out.println();
    }
}

interface ISolution {
    int[] maxDepthAfterSplit(String seq);
}

class Solution implements ISolution {
    @Override
    public int[] maxDepthAfterSplit(String seq) {
        if (seq == null) return null;

        int[] answer = new int[seq.length()];
        int left = 0;
        int right = 0;
        for (int i = 0; i < seq.length(); i++) {
            // add evenly
            if (seq.charAt(i) == '(') {
                if (left > right) {
                    right++;
                    answer[i] = 1;
                } else {
                    left++;
                    answer[i] = 0;
                }
            }

            // remove most
            if (seq.charAt(i) == ')') {
                if (left > right) {
                    left--;
                    answer[i] = 0;
                } else {
                    right--;
                    answer[i] = 1;
                }
            }
        }

        return answer;
    }
}

/**
 * 根据奇偶性
 */
class Solution2 implements ISolution {
    @Override
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length(), res[] = new int[n];
        for (int i = 0; i < n; ++i)
            res[i] = seq.charAt(i) == '(' ? i & 1 : (1 - i & 1);
        return res;
    }
}
