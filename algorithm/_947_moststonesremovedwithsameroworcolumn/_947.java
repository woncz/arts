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

package _947_moststonesremovedwithsameroworcolumn;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by woncz on 1/18/2021.
 */
public class _947 {
    public static void main(String[] args) {
        int[][] stones = new int[][] {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        ISolution solution = new Solution();
        System.out.println(solution.removeStones(stones));
    }
}

interface ISolution {
    int removeStones(int[][] stones);
}

class Solution implements ISolution {

    Map<Integer, Integer> cache = new HashMap<>();

    int islands = 0;

    @Override
    public int removeStones(int[][] stones) {
        for (int[] stone : stones) {
            union(stone[0], ~stone[1]);
        }
        return stones.length - islands;
    }

    int find(int x) {
        if (cache.putIfAbsent(x, x) == null)
            islands++;
        if (x != cache.get(x))
            cache.put(x, find(cache.get(x)));
        return cache.get(x);
    }

    void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            cache.put(x, y);
            islands--;
        }
    }
}