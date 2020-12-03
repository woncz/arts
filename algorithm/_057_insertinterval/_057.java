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

package _057_insertinterval;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 心得：用数学的概念,去解析实际问题,思路清晰效果显著
 * Created by woncz on 12/2/2020.
 */
public class _057 {
    public static void main(String[] args) {
        int[][] intervals = new int[][] {{1, 3}, {6, 9}};
        int[] newInterval = new int[] {2, 5};
        ISolution solution = new Solution();
        int[][] ans = solution.insert(intervals, newInterval);
        for (int[] a : ans) {
            System.out.print(Arrays.toString(a));
        }

        System.out.println();

        intervals = new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        newInterval = new int[] {4, 8};
        ans = solution.insert(intervals, newInterval);
        for (int[] a : ans) {
            System.out.print(Arrays.toString(a));
        }
    }
}

interface ISolution {
    int[][] insert(int[][] intervals, int[] newInterval);
}

class Solution implements ISolution {
    @Override
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<Integer> low = new LinkedList<>(), high = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            int position = intersect(intervals[i], newInterval);
            switch (position) {
                case -1:
                    low.add(i);
                    break;
                case 0:
                    newInterval = union(intervals[i], newInterval);
                    break;
                case 1:
                    high.add(i);
                    break;
                default:
                    break;
            }
        }
        int n = low.size() + 1 + high.size();
        int[][] ans = new int[n][2];
        for (int i = 0, l = 0, h = 0; i < n; i++) {
            if (l < low.size()) {
                ans[i] = intervals[low.get(l++)];
            } else if (l == low.size()) {
                ans[i] = newInterval;
                l++;
            } else {
                ans[i] = intervals[high.get(h++)];
            }
        }
        return ans;
    }

    /**
     * 是否相交
     * @param source
     * @param interval
     * @return
     */
    int intersect(int[] source, int[] interval) {
        if (source[0] > interval[1]) return 1;
        if (source[1] < interval[0]) return -1;
        return 0;
    }

    /**
     * 并集
     * @param a
     * @param b
     * @return
     */
    int[] union(int[] a, int[] b) {
        return new int[] {Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }
}
