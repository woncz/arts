/*
 * Copyright [2021]
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

package _342_poweroffour;

/**
 * Created by woncz on 5/31/2021.
 */
public class _342 {
    public static void main(String[] args) {
        ISolution solution = new Solution();
        System.out.println(solution.isPowerOfFour(4));

        System.out.println(solution.isPowerOfFour(3));
    }
}

interface ISolution {
    boolean isPowerOfFour(int n);
}

class Solution implements ISolution {
    @Override
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xAAAAAAAA) == 0;
    }
}

class Solution2 implements ISolution {

    @Override
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 0;
    }
}
