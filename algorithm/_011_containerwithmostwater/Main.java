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

package _011_containerwithmostwater;

public class Main {

    public static void main(String[] args) {
        int[] data1 = new int[] {1,8,6,2,5,4,8,3,7};
        // 1. brute force
        ISolution bruteForce = new SolutionBruteForce();
        System.out.println(bruteForce.maxArea(data1));

        // 2. dynamic programming
        ISolution s = new Solution();
        System.out.println(s.maxArea(data1));
    }

}

interface ISolution {
    int maxArea(int[] height);
}

class Solution implements ISolution {

    @Override
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int maxArea = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            if (area > maxArea) maxArea = area;
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }
}

/**
 * time complexity : O(N)
 */
class SolutionBruteForce implements ISolution {

    @Override
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                if (area > maxArea) maxArea = area;
            }
        }
        return maxArea;
    }
}