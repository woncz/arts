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

package _454_4sumii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * sucks for myself
 * Created by woncz on 11/30/2020.
 */
public class _454 {
    public static void main(String[] args) {
        int[] A = new int[] {1, 2};
        int[] B = new int[] {-2, -1};
        int[] C = new int[] {-1, 2};
        int[] D = new int[] {0, 2};

        ISolution solution = new Solution3();
        System.out.println(solution.fourSumCount(A, B, C, D));

        A = new int[] {-1, -1};
        B = new int[] {-1, 1};
        C = new int[] {-1, 1};
        D = new int[] {1, -1};
        System.out.println(solution.fourSumCount(A,B,C,D));
    }
}

interface ISolution {
    int fourSumCount(int[] A, int[] B, int[] C, int[] D);
}

/**
 * Timeout
 */
class Solution implements ISolution {
    @Override
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A.length == 0 || B.length == 0 || C.length == 0 || D.length == 0) return 0;

        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < C.length; k++) {
                    for (int l = 0; l < D.length; l++) {
                        if (A[i] + B[j] + C[k] + D[l] == 0) {
                            System.out.println(i + "," + j + "," + k + "," + l);
                            cnt++;
                        }
                    }
                }
            }
        }

        return cnt;
    }
}

class Solution2 implements ISolution {
    @Override
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A.length == 0 || B.length == 0 || C.length == 0 || D.length == 0) return 0;
        Map<Integer, List<Integer>> HA = new TreeMap<>();
        Map<Integer, List<Integer>> HB = new TreeMap<>();
        Map<Integer, List<Integer>> HC = new TreeMap<>();
        Map<Integer, List<Integer>> HD = new TreeMap<>();

        for (int i = 0; i < A.length; i++) {
            List<Integer> index = HA.get(A[i]);
            if (index == null) {
                index = new ArrayList<>();
                HA.put(A[i], index);
            }
            index.add(i);
        }

        for (int i = 0; i < B.length; i++) {
            List<Integer> index = HB.get(B[i]);
            if (index == null) {
                index = new ArrayList<>();
                HB.put(B[i], index);
            }
            index.add(i);
        }

        for (int i = 0; i < C.length; i++) {
            List<Integer> index = HC.get(C[i]);
            if (index == null) {
                index = new ArrayList<>();
                HC.put(C[i], index);
            }
            index.add(i);
        }

        for (int i = 0; i < D.length; i++) {
            List<Integer> index = HD.get(D[i]);
            if (index == null) {
                index = new ArrayList<>();
                HD.put(D[i], index);
            }
            index.add(i);
        }

        int cnt = 0;
        for (Map.Entry<Integer, List<Integer>> ha : HA.entrySet()) {
            for (Map.Entry<Integer, List<Integer>> hb : HB.entrySet()) {
                for (Map.Entry<Integer, List<Integer>> hc : HC.entrySet()) {
                    for (Map.Entry<Integer, List<Integer>> hd : HD.entrySet()) {
                        if (ha.getKey() + hb.getKey() + hc.getKey() + hd.getKey() == 0) {
                            cnt += ha.getValue().size() * hb.getValue().size() * hc.getValue().size() * hd.getValue().size();
                        }
                    }
                }
            }
        }

        return cnt;
    }
}

/**
 * 通过合并处理降低复杂度，N^4 -> N^2
 */
class Solution3 implements ISolution {
    @Override
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A.length == 0 || B.length == 0 || C.length == 0 || D.length == 0) return 0;

        Map<Integer, Integer> sums = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                sums.put(sum, sums.getOrDefault(sum, 0)  + 1);
            }
        }

        int cnt = 0;
        for (int m = 0; m < C.length; m++) {
            for (int n = 0; n < D.length; n++) {
                int sum = C[m] + D[n];
                if (sums.containsKey(-sum)) {
                    cnt += sums.get(-sum);
                }
            }
        }

        return cnt;
    }
}