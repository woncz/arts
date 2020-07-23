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

package _210_coursescheduleii;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class _210 {
    public static void main(String[] args) {
        ISolution s = new Solution2();
        int numCourses = 2;
        int[][] prerequisites = new int[][] {
                new int[] {1, 0}
        };
        int[] ans = s.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(ans));


        numCourses = 4;
        prerequisites = new int[][] {
                new int[] {1, 0},
                new int[] {2, 0},
                new int[] {3, 1},
                new int[] {3, 2}
        };
        ans = s.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(ans));
    }
}

class Node {
    int value;
    Set<Node> children;
}

interface ISolution {
    int[] findOrder(int numCourses, int[][] prerequisites);
}

class Solution implements ISolution {
    @Override
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return new int[0];
    }
}

class Solution2 implements ISolution {
    @Override
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // output degree
        int[] degree = new int[numCourses];
        // adjacent links
        List<List<Integer>> adjacent = new ArrayList<>(numCourses);
        this.buildGraph(degree, adjacent, prerequisites);
        return this._bfs(degree, adjacent);
    }

    void buildGraph(int[] degree, List<List<Integer>> adjacent, int[][] prerequisites) {
        IntStream.range(0, degree.length).forEach(i -> adjacent.add(new ArrayList<>()));
        for (int[] edge : prerequisites) {
            degree[edge[0]]++;
            adjacent.get(edge[1]).add(edge[0]);
        }
    }

    int[] _bfs(int[] degree, List<List<Integer>> adjacent) {
        int[] order = new int[degree.length];
        Queue<Integer> toVisit = new ArrayDeque<>();

        // the roots
        IntStream.range(0, degree.length).forEach(i -> {
            if (degree[i] == 0) {
                toVisit.offer(i);
            }
        });

        int visited = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();
            order[visited++] = from;
            for (int to : adjacent.get(from)) {
                degree[to]--;
                if (degree[to] == 0) {
                    toVisit.offer(to);
                }
            }
        }

        return visited == degree.length ? order : new int[0];
    }
}