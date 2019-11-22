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

package _036_validsudoku;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class _036 {

    public static void main(String[] args) {

        char[][] data = new char[][] {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        Solution solution = new Solution();
        System.out.println(solution.isValidSudoku(data));
    }
}

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // cache first
        Map<Character, List<String>> cache = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j]) continue;
                if (cache.containsKey(board[i][j])) {
                    List<String> positions = cache.get(board[i][j]);
                    positions.add(i + "" + j);
                } else {
                    List<String> positions = new LinkedList<>();
                    positions.add(i + "" + j);
                    cache.put(board[i][j], positions);
                }
            }
        }

        // check
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j]) continue;
                List<String> positions = cache.get(board[i][j]);
                for (String p : positions) {
                    int x = Integer.parseInt(p.charAt(0) + "");
                    int y = Integer.parseInt(p.charAt(1) + "");
                    if (x == i && y == j) continue;
                    if (p.startsWith(i + "") || p.endsWith(j + "")) {
                        return false;
                    }
                    if (x / 3 == i / 3 && y / 3 == j / 3) {
                        return false;
                    }
                }

            }
        }
        return true;
    }
}

class Solution2 {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Integer>[] col = new HashMap[9];
        Map<Integer, Integer>[] row = new HashMap[9];
        Map<Integer, Integer>[] box = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            col[i] = new HashMap<>();
            row[i] = new HashMap<>();
            box[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' != board[i][j]) {
                }
            }
        }
        return true;
    }
}
