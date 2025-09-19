/*
 * Copyright [2023]
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

package _k3484_designspreadsheet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by live0 on 2025/9/19.
 */
public class K3484 {
    public static void main(String[] args) {
        Spreadsheet spreadsheet = new Spreadsheet(3); // 初始化一个具有 3 行和 26 列的电子表格
        spreadsheet.getValue("=5+7"); // 返回 12 (5+7)
        spreadsheet.setCell("A1", 10); // 设置 A1 为 10
        spreadsheet.getValue("=A1+6"); // 返回 16 (10+6)
        spreadsheet.setCell("B2", 15); // 设置 B2 为 15
        spreadsheet.getValue("=A1+B2"); // 返回 25 (10+15)
        spreadsheet.resetCell("A1"); // 重置 A1 为 0
        spreadsheet.getValue("=A1+B2"); // 返回 15 (0+15)
    }
}

class Spreadsheet {

    int[][] grid;

    public Spreadsheet(int rows) {
        grid = new int[26][rows];
    }

    public void setCell(String cell, int value) {
        int[] xy = getXY(cell);
        int x = xy[0];
        int y = xy[1];
        grid[x][y] = value;
    }

    public void resetCell(String cell) {
        int[] xy = getXY(cell);
        int x = xy[0];
        int y = xy[1];
        grid[x][y] = 0;
    }

    public int getValue(String formula) {
        formula = formula.substring(1);
        String[] cells = formula.split("\\+");
        return getV(cells[0]) + getV(cells[1]);
    }

    boolean isNumeric(String s) {
        return s.matches("\\d+");
    }

    public int getV(String cell) {
        if (this.isNumeric(cell)) {
            return Integer.parseInt(cell);
        }
        int[] xy = getXY(cell);
        int x = xy[0];
        int y = xy[1];
        return grid[x][y];
    }

    int[] getXY(String cell) {
        int x = cell.charAt(0) - 'A';
        int y = Integer.parseInt(cell.substring(1)) - 1;
        return new int[] {x,y};
    }
}

class Spreadsheet2 {

    private Map<String, Integer> cellValues = new HashMap<>();

    public Spreadsheet2(int rows) {

    }

    public void setCell(String cell, int value) {
        cellValues.put(cell, value);
    }

    public void resetCell(String cell) {
        cellValues.remove(cell);
    }

    public int getValue(String formula) {
        int i = formula.indexOf('+');
        String cell1 = formula.substring(1, i);
        String cell2 = formula.substring(i + 1);
        int val1 = Character.isLetter(cell1.charAt(0)) ? cellValues.getOrDefault(cell1, 0) : Integer.parseInt(cell1);
        int val2 = Character.isLetter(cell2.charAt(0)) ? cellValues.getOrDefault(cell2, 0) : Integer.parseInt(cell2);
        return val1 + val2;
    }

}
