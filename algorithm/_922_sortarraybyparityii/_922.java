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

package _922_sortarraybyparityii;

/**
 * Created by woncz on 11/12/2020.
 */
public class _922 {
    public static void main(String[] args) {
        int[] A = new int[] {4,2,5,7};
        ISolution solution = new Solution();
        solution.sortArrayByParityII(A);
        for (int i : A) {
            System.out.print(i + " ");
        }
    }
}

interface ISolution {
    int[] sortArrayByParityII(int[] A);
}

class Solution implements ISolution {
    @Override
    public int[] sortArrayByParityII(int[] A) {
        int i = 0, j = 1;
        while (i < A.length && j < A.length) {
            while (i < A.length && (A[i] & 1) == 0) {
                i += 2;
            }
            while (j < A.length && (A[j] & 1) == 1) {
                j += 2;
            }

            if (i < A.length - 1) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        return A;
    }
}
