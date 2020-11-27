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

package _657_robotreturntoorigin;

/**
 * Created by woncz on 8/28/2020.
 */
public class _657 {
    public static void main(String[] args) {
        String moves = "UD";
        ISolution solution = new Solution();
        System.out.println(solution.judgeCircle(moves));

        moves = "LL";
        System.out.println(solution.judgeCircle(moves));

    }
}

interface ISolution {
    boolean judgeCircle(String moves);
}

class Solution implements ISolution {
    @Override
    public boolean judgeCircle(String moves) {
        if (moves == null || moves.isEmpty()) return true;

        int x = 0, y = 0;
        for (char m : moves.toCharArray()) {
            switch (m) {
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'R':
                    x++;
                    break;
                case 'L':
                    x--;
                    break;
            }
        }

        return x == 0 && y == 0;
    }
}