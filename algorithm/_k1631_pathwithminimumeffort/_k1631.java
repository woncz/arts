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

package _k1631_pathwithminimumeffort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by woncz on 1/29/2021.
 */
public class _k1631 {
    public static void main(String[] args) {
        int[][] heights = new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        ISolution solution = new Solution();
        System.out.println(solution.minimumEffortPath(heights));
    }
}

interface ISolution {
    int minimumEffortPath(int[][] heights);
}

class Solution implements ISolution {
    @Override
    public int minimumEffortPath(int[][] heights) {
        int[] DIR = new int[]{0, 1, 0, -1, 0};
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        //init [distance, row, col]
        pq.add(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int d = current[0], r = current[1], c = current[2];
            if (d > dist[r][c]) continue;
            // reach the end
            if (r == m - 1 && c == n - 1) return d;

            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i], nc = c + DIR[i + 1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nd = Math.max(d, Math.abs(heights[nr][nc] - heights[r][c]));
                    if (dist[nr][nc] > nd) {
                        dist[nr][nc] = nd;
                        pq.offer(new int[]{nd, nr, nc});
                    }
                }
            }
        }

        return 0;
    }
}