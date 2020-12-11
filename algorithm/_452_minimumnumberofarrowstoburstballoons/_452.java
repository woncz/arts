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

package _452_minimumnumberofarrowstoburstballoons;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by woncz on 12/1/2020.
 */
public class _452 {
    public static void main(String[] args) {
        int[][] points = new int[][] {{7,10}, {1,5}, {3,6}, {2,4}, {1,4}};
        ISolution solution = new Solution();
        System.out.println(solution.findMinArrowShots(points));
    }
}

interface ISolution {
    int findMinArrowShots(int[][] points);
}

class Solution implements ISolution {
    @Override
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int cnt = 1;
        int pos = points[0][1];
        //System.out.println("pos=" + pos);
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= pos) {
                continue;
            }
            cnt++;
            pos = points[i][1];
            //System.out.println("pos=" + pos);
        }

        return cnt;
    }
}
