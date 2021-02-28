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

package _896_monotonicarray;


/**
 * Created by woncz on 2/28/2021.
 */
public class _896 {
    public static void main(String[] args) {
        ISolution solution = new Solution2();
        int[] A = new int[] {1,2,2,3};
        System.out.println(solution.isMonotonic(A));

        A = new int[] {1,1,1};
        System.out.println(solution.isMonotonic(A));
    }
}


interface ISolution {
    boolean isMonotonic(int[] A);
}

class Solution implements ISolution {
    @Override
    public boolean isMonotonic(int[] A) {
        boolean ans = true;
        int flag = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] == A[i + 1]) continue;
            if (A[i] > A[i + 1]) {
                if (flag < 0) return false;
                if (flag == 0) flag = 1;
            } else {
                if (flag > 0) return false;
                if (flag == 0) flag = -1;
            }
        }
        return ans;
    }
}

class Solution2 implements ISolution {
    @Override
    public boolean isMonotonic(int[] A) {
        boolean inc = true, dec = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] < A[i + 1]) {
                dec = false;
            }
            if (A[i] > A[i + 1]) {
                inc = false;
            }
        }
        return inc || dec;
    }
}