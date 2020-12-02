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

package _321_createmaximumnumber;

import java.util.Arrays;

/**
 * Created by woncz on 12/2/2020.
 */
public class _321 {
    public static void main(String[] args) {
        int[] nums1 = new int[] {3, 4, 6, 5};
        int[] nums2 = new int[] {9, 1, 2, 5, 8, 3};
        int k = 5;
        ISolution solution = new Solution2();
        System.out.println(Arrays.toString(solution.maxNumber(nums1, nums2, k)));

        nums1 = new int[] {6, 7};
        nums2 = new int[] {6, 0, 4};
        k = 5;
        System.out.println(Arrays.toString(solution.maxNumber(nums1, nums2, k)));

        nums1 = new int[] {3, 9};
        nums2 = new int[] {8, 9};
        k = 3;
        System.out.println(Arrays.toString(solution.maxNumber(nums1, nums2, k)));
    }
}

interface ISolution {
    int[] maxNumber(int[] nums1, int[] nums2, int k);
}

class Solution implements ISolution {
    @Override
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        int l1 = 0, r1 = 0, l2 = 0, r2 = 0;
        for (int i = 0; i < k; i++) {
            int rt = r1;
            if (nums2.length - r2 >= (k - i)) {
                r1 = nums1.length;
            } else {
                r1 = nums1.length + nums2.length - r2 - (k -i);
            }
            if (nums1.length - rt >= (k - i)) {
                r2 = nums2.length;
            } else {
                r2 = nums2.length + nums1.length - rt - (k - i);
            }

            int n = 0, which = -1, pos = -1;
            for (int p = l1; p < r1; p++) {
                if (nums1[p] > n) {
                    n = nums1[p];
                    which = 1;
                    pos = p;
                    l1 = p + 1;
                }
            }
            for (int p = l2; p < r2; p++) {
                if (nums2[p] > n) {
                    n = nums2[p];
                    which = 2;
                    pos = p;
                    l2 = p + 1;
                }
            }
            System.out.println(n);
            ans[i] = n;
        }

        return ans;
    }
}

class Solution2 implements ISolution {

    @Override
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        int[] ans = new int[k];
        for (int selected = Math.max(0, k - m); selected <= k && selected <= n; selected++) {
            int[] candidate = mergeToMax(maxArray(nums1, selected), maxArray(nums2, k - selected), k);
            if (greater(candidate, 0, ans, 0)) ans = candidate;
        }
        return ans;
    }

    /**
     * 两个数组合并最大
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    int[] mergeToMax(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; r++) {
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return ans;
    }

    /**
     * 比较两个数组大小
     *
     * @param nums1
     * @param i1
     * @param nums2
     * @param i2
     * @return
     */
    boolean greater(int[] nums1, int i1, int[] nums2, int i2) {
        while (i1 < nums1.length && i2 < nums2.length && nums1[i1] == nums2[i2]) {
            i1++; i2++;
        }
        return i2 == nums2.length || (i1 < nums1.length && nums1[i1] > nums2[i2]);
    }

    /**
     * 单个数组最大组成
     * @param nums
     * @param k
     * @return
     */
    int[] maxArray(int[] nums, int k) {
        int[] ans = new int[k];
        int N = nums.length;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            while (N - i + cnt > k && cnt > 0 && ans[cnt - 1] < nums[i]) {
                cnt--; // 存在更优候选,逐次需要后退,重新选择,直至最优(复杂度如何计算)
            }
            if (cnt < k) {
                ans[cnt++] = nums[i];
            }
        }
        return ans;
    }
}
