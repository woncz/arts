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

package _841_keysandrooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by woncz on 8/31/2020.
 */
public class _841 {
    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(3));
        rooms.add(Collections.emptyList());

        ISolution solution = new Solution();
        System.out.println(solution.canVisitAllRooms(rooms));

        rooms.clear();
        rooms.add(Arrays.asList(1,3));
        rooms.add(Arrays.asList(3,0,1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));

        solution = new Solution();
        System.out.println(solution.canVisitAllRooms(rooms));
    }
}

interface ISolution {
    boolean canVisitAllRooms(List<List<Integer>> rooms);
}

class Solution implements ISolution {

    boolean visited = false;

    @Override
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() <= 1) return true;
        this._dfs(new HashMap<>(), 0, rooms);
        return visited;
    }

    void _dfs(Map<Integer, Integer> prefix, int index, List<List<Integer>> rooms) {
        // terminator
        if (visited || index == rooms.size()) return;

        List<Integer> candidate = rooms.get(index);
        for (Integer i : candidate) {
            if (!prefix.containsKey(i)) {
                prefix.put(i, 1);
                boolean v = this.visitAll(prefix, rooms.size());
                if (v) {
                    visited = true;
                } else {
                    this._dfs(prefix, i, rooms);
                }
            }
        }
    }

    boolean visitAll(Map<Integer, Integer> prefix, int N) {
        for (Integer i = 1; i < N; i++) {
            if (!prefix.containsKey(i)) return false;
        }
        return true;
    }
}