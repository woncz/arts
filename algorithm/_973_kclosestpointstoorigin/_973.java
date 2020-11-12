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

package _973_kclosestpointstoorigin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by woncz on 11/9/2020.
 */
public class _973 {
    public static void main(String[] args) {
        int[][] points = new int[][] {{-173,399},{62,-213},{71,282},{-45,851},{710,982},{493,985},{-529,-946},{-83,78},{624,-785},{877,-351},{500,-376},{-601,-305},{-23,-79},{-82,906},{-143,500},{-249,-260},{10,706},{-904,-632},{-402,458},{303,-970},{93,-552},{-362,-743},{705,986},{900,-524},{-680,-204},{-726,890},{-138,742},{-76,714},{813,474},{443,23},{-422,117},{768,214},{863,562},{728,-204},{778,147},{-56,-751},{240,454},{-106,-701},{-897,-770},{572,433},{-658,97},{-301,-466},{902,-371},{-38,-662},{-872,191},{659,294},{852,965},{-37,665},{541,-920},{-537,704}};
        int K = 20;
        ISolution solution = new Solution4();
        int[][] ans = solution.kClosest(points, K);
        for (int[] p : ans) {
            System.out.println(Arrays.toString(p));
        }

        points = new int[][] {{3,3}, {5, -1}, {-2, 4}};
        K = 2;
        ans = solution.kClosest(points, K);
        for (int[] p : ans) {
            System.out.println(Arrays.toString(p));
        }
    }
}

interface ISolution {
    int[][] kClosest(int[][] points, int K);
}

class Solution implements ISolution {
    @Override
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0 || points[0].length == 0) return null;
        if (points.length <= K) return points;
        PriorityQueue<int[]> pq = new PriorityQueue<>(points.length, (o1, o2) -> {
            int d1 = o1[0] * o1[0] + o1[1] * o1[1];
            int d2 = o2[0] * o2[0] + o2[1] * o2[1];
            if (d1 < d2) return -1;
            if (d1 > d2) return 1;
            return 0;
        });

        for (int[] p : points) {
            pq.add(p);
        }

        int[][] ans = new int[K][2];
        for (int i = 0; i < K; i++) {
            ans[i] = pq.remove();
        }

        return ans;
    }
}

class Solution2 implements ISolution {
    @Override
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparingInt(p -> (p[0] * p[0] + p[1] * p[1])));
        return Arrays.copyOfRange(points, 0, K);
    }
}

class Solution3 implements ISolution {
    @Override
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0 || points[0].length == 0) return null;
        if (points.length <= K) return points;

        PriorityQueue<int[]> pq = new PriorityQueue<>(K, (p1, p2) -> p2[0] - p1[0]);
        for (int i = 0; i < K; i++) {
            pq.offer(new int[] {points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        for (int i = K; i < points.length; i++) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (dist < pq.peek()[0]) {
                pq.poll();
                pq.offer(new int[] {dist, i});
            }
        }

        int[][] ans = new int[K][2];
        for (int i = 0; i < K; i++) {
            ans[i] = points[pq.poll()[1]];
        }
        return ans;
    }
}

class Solution4 implements ISolution {

    Random random = new Random();

    @Override
    public int[][] kClosest(int[][] points, int K) {
        this.quickSelect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    /**
     * 模拟快速排序
     */
    void quickSelect(int[][] points, int left, int right, int K) {
        int pivot = left + random.nextInt(right - left + 1);
        int d = points[pivot][0] * points[pivot][0] + points[pivot][1] * points[pivot][1];
        swap(points, pivot, right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
            if (dist <= d) {
                i++;
                swap(points, i, j);
            }
        }
        i++;
        swap(points, i, right);
        if (K < i - left + 1) {
            quickSelect(points, left, i - 1, K);
        } else if (K > i - left + 1) {
            quickSelect(points, i + 1, right, K - (i - left + 1));
        }
    }

    void swap(int[][] points, int left, int right) {
        int[] tmp = points[left];
        points[left] = points[right];
        points[right] = tmp;
    }
}