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

package _offer11_xuanzhuanshuzudezuixiaoshuzilcof;

/**
 * Created by woncz on 7/22/2020.
 */
public class _offer11 {
    public static void main(String[] args) {
        int[] numbers = new int[] {3,4,5,1,2};

        ISolution solution = new SolutionBinarySearch();
        System.out.println(solution.minArray(numbers));

        numbers = new int[] {2,2,2,0,1};
        System.out.println(solution.minArray(numbers));

        numbers = new int[] {1,3,5};
        System.out.println(solution.minArray(numbers));
    }
}

interface ISolution {
    int minArray(int[] numbers);
}

/**
 * 最小值：左边大，右边大，你就是最小，
 */
class Solution implements ISolution {
    @Override
    public int minArray(int[] numbers) {
        if (numbers == null) return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }
}

/**
 * 二分查找，
 */
class SolutionBinarySearch implements ISolution {

    @Override
    public int minArray(int[] numbers) {
        if (numbers == null) return 0;
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right = right - 1;
            }
        }
        return numbers[left];
    }
}
