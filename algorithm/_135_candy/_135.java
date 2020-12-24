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

package _135_candy;

import java.util.Arrays;

/**
 * Created by woncz on 12/24/2020.
 */
public class _135 {
    public static void main(String[] args) {
        int[] ratings = new int[] {1, 0, 2};
        ISolution solution = new Solution2();
        System.out.println(solution.candy(ratings));

        ratings = new int[] {1, 2, 2};
        System.out.println(solution.candy(ratings));
    }
}

interface ISolution {
    int candy(int[] ratings);
}

class Solution implements ISolution {
    @Override
    public int candy(int[] ratings) {
        int[] cache = new int[ratings.length];
        Arrays.fill(cache, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                cache[i] = cache[i - 1] + 1;
            } else if (ratings[i] < ratings[i - 1] && cache[i] >= cache[i - 1]) {
                cache[i - 1] = cache[i] + 1;
                for (int j = i - 2; j >= 0; j--) {
                    if (ratings[j] > ratings[j + 1] && cache[j] <= cache[j + 1]) {
                        cache[j] = cache[j + 1] + 1;
                    }
                }
            }
        }

        int ans = 0;
        for (int c : cache) {
            ans += c;
        }
        return ans;
    }
}

class Solution2 implements ISolution {
    @Override
    public int candy(int[] ratings) {
        int[] cache = new int[ratings.length];
        Arrays.fill(cache, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) cache[i] = cache[i - 1] + 1;
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) cache[i] = Math.max(cache[i], cache[i + 1] + 1);
        }
        int ans = 0;
        for (int c : cache) {
            ans += c;
        }
        return ans;
    }
}