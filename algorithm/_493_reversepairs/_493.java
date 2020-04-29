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

package _493_reversepairs;

public class _493 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,3,2,3,1};
        ISolution s1 = new Solution();
        int ans = s1.reversePairs(nums);
        System.out.println(ans);

        nums = new int[] {2,4,3,5,1};
        ans = s1.reversePairs(nums);
        System.out.println(ans);
    }
}

interface ISolution {
    int reversePairs(int[] nums);
}

/**
 * brute-force
 */
class Solution implements ISolution {
    @Override
    public int reversePairs(int[] nums) {
        if (nums == null) return 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > 2L * nums[j]) cnt++;
            }
        }
        return cnt;
    }
}

/**
 * divide & conquer
 */
class Solution2 implements ISolution {
    @Override
    public int reversePairs(int[] nums) {
        return 0;
    }
}
