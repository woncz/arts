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

package _074_searcha2dmatrix;

public class _074 {
    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        ISolution s2 = new Solution2();
        boolean ans = s2.searchMatrix(nums, 11);
        System.out.println(ans);

        ISolution s3 = new Solution3();
        ans = s3.searchMatrix(nums, 13);
        System.out.println(ans);

        ans = s2.searchMatrix(nums, 13);
        System.out.println(ans);

        nums = new int[][]{{1}};
        ans = s2.searchMatrix(nums, 3);
        System.out.println(ans);


    }
}

interface ISolution {
    boolean searchMatrix(int[][] matrix, int target);
}

/**
 * 暴力法，顺序查找
 * time complexity : O(m * n)
 */
class Solution1 implements ISolution {
    @Override
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == target) return true;
            }
        }
        return false;
    }
}

/**
 * 二分法，二维
 */
class Solution2 implements ISolution {
    @Override
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;

        // 行[0]，前一行小，后一行大
        int up = 0, down = m - 1;
        while (up < down) {
            int mid = up + (down - up) / 2;
            if (matrix[mid][0] == target) return true;
            if (matrix[mid][0] > target) {
                down = mid - 1;
            } else {
                if (matrix[mid][n - 1] == target) return true;
                if (matrix[mid][n - 1] < target) {
                    up = mid + 1;
                } else {
                    up = mid;
                    break;
                }
            }
        }
        int current = up;
        // 列，普通数组的二分法
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[current][mid] == target) return true;
            if (matrix[current][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}

/**
 * 二分法 二维转一维(其实就是一条曲线)
 */
class Solution3 implements ISolution {
    @Override
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = n * m - 1;
        while (left <= right) {
            int mid = left + (right - left) >> 1;
            if (matrix[mid / n][mid % n] == target) return true;
            if (matrix[mid / n][mid % n] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}