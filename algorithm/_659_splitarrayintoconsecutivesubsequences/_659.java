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

package _659_splitarrayintoconsecutivesubsequences;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by woncz on 12/10/2020.
 */
public class _659 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,3,4,5};
        ISolution solution = new Solution2();
        System.out.println(solution.isPossible(nums));

        nums = new int[] {1,2,3,3,4,4,5,5};
        solution = new Solution2();
        System.out.println(solution.isPossible(nums));
    }
}

interface ISolution {
    boolean isPossible(int[] nums);
}

class Solution implements ISolution {
    @Override
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(), appendFreq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            } else if (appendFreq.getOrDefault(num, 0) > 0) {
                appendFreq.put(num, appendFreq.get(num) - 1);
                appendFreq.put(num + 1, appendFreq.getOrDefault(num + 1, 0) + 1);
            } else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                appendFreq.put(num + 3, appendFreq.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
            freq.put(num, freq.get(num) - 1);
        }
        return true;
    }
}

class Solution2 implements ISolution {
    @Override
    public boolean isPossible(int[] nums) {
        PriorityQueue<Interval> pq = new PriorityQueue<>((i1, i2) -> {
            if (i1.end == i2.end) {
                return Integer.compare(i1.length, i2.length);
            }
            return Integer.compare(i1.end, i2.end);
        });

        for (int num : nums) {
            while (!pq.isEmpty() && pq.peek().end + 1 < num) {
                if (pq.poll().length < 3) {
                    return false;
                }
            }
            if (pq.isEmpty() || pq.peek().end == num) {
                pq.add(new Interval(num, num));
            } else {
                pq.add(new Interval(pq.poll().start, num));
            }
        }
        while (!pq.isEmpty()) {
            if (pq.poll().length < 3) {
                return false;
            }
        }
        return true;
    }

    private static class Interval {
        int start;
        int end;
        int length;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            this.length = end - start + 1;
        }
    }
}