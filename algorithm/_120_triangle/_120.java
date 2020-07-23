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

package _120_triangle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 7/22/2020.
 */
public class _120 {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new LinkedList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));

        ISolution solution = new Solution2();
        System.out.println(solution.minimumTotal(triangle));
    }
}

interface ISolution {
    int minimumTotal(List<List<Integer>> triangle);
}

/**
 * 由上向下
 */
class Solution implements ISolution {
    @Override
    public int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.get(triangle.size() - 1).size();
        int[] dp = new int[N];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = triangle.get(i).size() - 1; j >= 0 ; j--) {
                int c = triangle.get(i).get(j);
                if (j == triangle.get(i).size() - 1) {
                    dp[j] = dp[j - 1] + c;
                } else if (j == 0) {
                    dp[j] = dp[j] + c;
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + c;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        Arrays.sort(dp);
        return dp[0];
    }
}

/**
 * 自底向上
 */
class Solution2 implements ISolution {
    @Override
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}