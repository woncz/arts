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

package _435_nonoverlappingintervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by woncz on 12/31/2020.
 */
public class _435 {
    public static void main(String[] args) {
        int[][] intervals = new int[][] { {1,2}, {2,3}, {3,4}, {1,3}};
        ISolution solution = new Solution();
        System.out.println(solution.eraseOverlapIntervals(intervals));
    }
}

interface ISolution {
    int eraseOverlapIntervals(int[][] intervals);
}

class Solution implements ISolution {
    @Override
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int end = intervals[0][1];
        int cnt = 1;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                cnt++;
            }
        }
        return intervals.length - cnt;
    }
}
