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

package _621_taskscheduler;

import java.util.Arrays;

/**
 * Created by woncz on 12/9/2020.
 */
public class _621 {
    public static void main(String[] args) {
        String tasks = "AAAABBBEEFFGG";
        int n = 3;
        ISolution solution = new Solution();
        System.out.println(solution.leastInterval(tasks.toCharArray(), n));
    }
}

interface ISolution {
    int leastInterval(char[] tasks, int n);
}

class Solution implements ISolution {
    @Override
    public int leastInterval(char[] tasks, int n) {
        int[] stat = new int[26];
        for (char task : tasks) {
            stat[task - 'A']++;
        }

        Arrays.sort(stat);
        int i = 25;
        while (i >= 0 && stat[i] == stat[25]) i--;

        return Math.max(tasks.length, (stat[25] - 1) * (n + 1) + 25 - i);
    }
}