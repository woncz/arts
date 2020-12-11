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

package _406_queuereconstructionbyheight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by woncz on 11/16/2020.
 */
public class _406 {
    public static void main(String[] args) {
        int[][] people = new int[][] {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        ISolution solution = new Solution();
        solution.reconstructQueue(people);
        for (int[] p : people) {
            System.out.println(Arrays.toString(p));
        }
    }
}

interface ISolution {
    int[][] reconstructQueue(int[][] people);
}

class Solution implements ISolution {
    @Override
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> {
            if (p1[0] != p2[0]) {
                return p2[0] - p1[0];
            } else {
                return p1[1] - p2[1];
            }
        });

        List<int[]> cache = new ArrayList<>(people.length);
        for (int[] p : people) {
            cache.add(p[1], p);
        }

        return cache.toArray(new int[cache.size()][]);
    }
}