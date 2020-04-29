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

package _056_mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _056 {
    public static void main(String[] args) {
        int[][] intervals = new int[][] {new int[]{-10, 0}, new int[]{20, 60}, new int[] {1, 3}, new int[] {2, 6}};
        ISolution s = new Solution();
        int[][] ans = s.merge(intervals);
        for (int[] i : ans) {
            System.out.println(Arrays.toString(i));
        }
    }
}


interface ISolution {
    int[][] merge(int[][] intervals);
}

class Solution implements ISolution {
    @Override
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 1) return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> res = new ArrayList<>();
        int[] t = intervals[0];
        res.add(t);
        for (int[] i : intervals) {
            if (i[0] <= t[1]) {
                t[1] = Math.max(i[1], t[1]);
            } else {
                t = i;
                res.add(i);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
