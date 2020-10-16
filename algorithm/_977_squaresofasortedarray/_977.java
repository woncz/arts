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

package _977_squaresofasortedarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by woncz on 10/16/2020.
 */
public class _977 {
    public static void main(String[] args) {
        ISolution solution = new Solution2();
        int[] A = new int[] {-4,-1,0,3,10};
        System.out.println(Arrays.toString(solution.sortedSquares(A)));

        A = new int[] {-7,-3,2,3,11};
        System.out.println(Arrays.toString(solution.sortedSquares(A)));
    }
}

interface ISolution {
    int[] sortedSquares(int[] A);
}

class Solution implements ISolution {
    @Override
    public int[] sortedSquares(int[] A) {
        int[] AA = new int[A.length];

        // 找出绝对值最小处
        int p = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                p = i;
            } else {

            }
        }

        // 从中间向两边
        int i = 0;
        int left = p, right = p;
        while (left >= 0 && right < A.length) {
            if (Math.abs(A[left]) <= Math.abs(A[right])) {
                AA[i++] = A[left] * A[left];
                if (left == right) right++;
                left--;
            } else {
                AA[i++] = A[right] * A[right];
                right++;
            }
        }

        // 左剩余
        while (left >= 0) {
            AA[i++] = A[left] * A[left];
            left--;
        }

        // 右剩余
        while(right < A.length) {
            AA[i++] = A[right] * A[right];
            right++;
        }

        return AA;
    }
}

class Solution2 implements ISolution {
    @Override
    public int[] sortedSquares(int[] A) {
        List<Integer> l = new ArrayList<>(A.length);
        for (int a : A) {
            l.add(a * a);
        }
        Collections.sort(l);
        int[] AA = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            AA[i] = l.get(i);
        }
        return AA;
    }
}


class Solutione implements ISolution {

    @Override
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int negative = -1;
        for (int i = 0; i < n; ++i) {
            if (A[i] < 0) {
                negative = i;
            } else {
                break;
            }
        }

        int[] ans = new int[n];
        int index = 0, i = negative, j = negative + 1;
        while (i >= 0 || j < n) {
            if (i < 0) {
                ans[index] = A[j] * A[j];
                ++j;
            } else if (j == n) {
                ans[index] = A[i] * A[i];
                --i;
            } else if (A[i] * A[i] < A[j] * A[j]) {
                ans[index] = A[i] * A[i];
                --i;
            } else {
                ans[index] = A[j] * A[j];
                ++j;
            }
            ++index;
        }

        return ans;
    }
}
