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

package _207_courseschedule;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Created by woncz on 8/4/2020.
 */
public class _207 {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[][] {{1, 0}};
        ISolution solution = new Solution();
        System.out.println(solution.canFinish(numCourses, prerequisites));

        numCourses = 2;
        prerequisites = new int[][] {{1, 0}, {0, 1}};
        System.out.println(solution.canFinish(numCourses, prerequisites));

        numCourses = 3;
        prerequisites = new int[][] {{0, 1}, {1, 2}, {2, 0}};
        System.out.println(solution.canFinish(numCourses, prerequisites));
    }
}

interface ISolution {
    boolean canFinish(int numCourses, int[][] prerequisites);
}

/**
 * indegree and outdegree
 * find the nodes which indegree is 0 everytime, if conflicts return false
 */
class Solution implements ISolution {
    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1) return true;

        // input degree
        int[] degree = new int[numCourses];

        // graph structure
        List<List<Integer>> adjacent = new ArrayList<>(numCourses);
        IntStream.range(0, numCourses).forEach(i -> adjacent.add(new LinkedList<>()));

        // init graph [1, 0] 0->1
        this._buildGraph(degree, adjacent, prerequisites);

        // traverse graph
        int cnt = this._dfs(degree, adjacent);
        return cnt == numCourses;
    }

    /**
     * build
     * @param degree
     * @param adjacent
     * @param prerequisites
     */
    void _buildGraph(int[] degree, List<List<Integer>> adjacent, int[][] prerequisites) {
        for (int[] p : prerequisites) {
            degree[p[0]]++;
            adjacent.get(p[1]).add(p[0]);
        }
    }

    /**
     * traverse
     * @param degree
     * @param adjacent
     */
    int _bfs(int[] degree, List<List<Integer>> adjacent) {
        // add roots
        Queue<Integer> toVisit = new ArrayDeque<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                toVisit.offer(i);
            }
        }

        int cnt = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();
            cnt++;
            for (int to : adjacent.get(from)) {
                degree[to]--;
                if (degree[to] == 0) {
                    toVisit.offer(to);
                }
            }
        }
        return cnt;
    }

    int _dfs(int[] degree, List<List<Integer>> adjacent) {
        // add roots
        Stack<Integer> toVisit = new Stack();
        for (int v : degree) {
            if (v == 0) {
                toVisit.push(v);
            }
        }

        int cnt = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.pop();
            cnt++;
            for (int to : adjacent.get(from)) {
                degree[to]--;
                if (degree[to] == 0) {
                    toVisit.push(to);
                }
            }
        }
        return cnt;
    }
}