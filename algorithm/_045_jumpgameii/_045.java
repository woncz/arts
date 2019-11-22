/*
 * Copyright [2017]
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

package _045_jumpgameii;

public class _045 {
    public static void main(String[] args) {
        int[] a = new int[] {2,3,1,1,4};
        ISolution s1 = new Solution1();
        int steps = s1.jump(a);
        System.out.println(steps);

    }
}

interface ISolution {
    int jump(int[] nums);
}

class Solution1 implements ISolution {
    @Override
    public int jump(int[] nums) {
        int steps = 0;
        int currentFarthest = 0;
        int nextFarthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            nextFarthest = Math.max(nextFarthest, nums[i] + i);
            if (i == currentFarthest) {
                steps++;
                currentFarthest = nextFarthest;
            }
        }

        return steps;
    }
}