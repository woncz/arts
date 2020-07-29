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

package _k1025_divisorgame;

/**
 * Created by woncz on 7/24/2020.
 */
public class _k1025 {
    public static void main(String[] args) {
        int N = 1000;
        ISolution solution = new Solution();
        for (int i = 1; i <= N; i++) {
            boolean ans = solution.divisorGame(i);
            System.out.println("N = "+ i + ", " + (ans ? "True" : "False"));
        }
    }
}

interface ISolution {
    boolean divisorGame(int N);
}

class Solution implements ISolution {
    @Override
    public boolean divisorGame(int N) {
        // true:first person wins, false:first person loses
        boolean[] cache = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            // 默认我会输，但是如果所有余数中存在输（轮到对方选了），则我会赢，因为我一定会让对方选这个余数
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !cache[i - j]) {
                    cache[i] = true;
                }
            }
        }

        return cache[N];
    }
}
