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

package _649_dota2senate;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 冤冤相报何时了
 * Created by woncz on 12/11/2020.
 */
public class _649 {
    public static void main(String[] args) {
        String senate = "RD";
        ISolution solution = new Solution2();
        System.out.println(solution.predictPartyVictory(senate));

        senate = "RDD";
        solution = new Solution2();
        System.out.println(solution.predictPartyVictory(senate));
    }
}

interface ISolution {
    String predictPartyVictory(String senate);
}

class Solution implements ISolution {
    @Override
    public String predictPartyVictory(String senate) {
        Queue<Integer> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        int n = senate.length();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') q1.add(i);
            else q2.add(i);
        }

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Integer i1 = q1.poll(), i2 = q2.poll();
            if (i1 < i2) q1.add(i1 + n);
            else q2.add(i2 + n);
        }
        return q1.size() > q2.size() ? "Radiant" : "Dire";
    }
}

class Solution2 implements ISolution {
    @Override
    public String predictPartyVictory(String senate) {
        int radiantCnt = 0, direCnt = 0, radiantMuteCnt = 0, direMuteCnt = 0;
        int n = senate.length();
        char mute = 'M';

        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                radiantCnt++;
            } else {
                direCnt++;
            }
        }

        int i = 0;
        char[] s = senate.toCharArray();
        while (radiantCnt > 0 && direCnt > 0) {
            if (s[i] == 'R') {
                if (radiantMuteCnt == 0) {
                    direMuteCnt++;
                } else {
                    s[i] = mute;
                    radiantCnt--;
                    radiantMuteCnt--;
                }
            } else if (s[i] == 'D') {
                if (direMuteCnt == 0) {
                    radiantMuteCnt++;
                } else {
                    s[i] = mute;
                    direCnt--;
                    direMuteCnt--;
                }
            }

            // next round
            i = (i + 1) % n;
        }

        return radiantCnt > 0 ? "Radiant" : "Dire";
    }
}