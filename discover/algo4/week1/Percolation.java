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

package algo4.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * key point : really understand the meaning of full-opened site
 * (just like rain down, which can get water - my easy understanding)
 */
public class Percolation {

    private final boolean[] canvas;

    private final int size;

    private int counter;

    private final WeightedQuickUnionUF grid;

    private final WeightedQuickUnionUF full;

    private int top;

    private int bottom;

    private final int[] dx = {-1, 1, 0, 0};

    private final int[] dy = {0, 0, -1, 1};

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        size = n;
        canvas = new boolean[n * n];

        grid = new WeightedQuickUnionUF(size * size + 2);
        full = new WeightedQuickUnionUF(size * size + 1); // prevent backwash

        top = reduct(size, size) + 1;
        bottom = reduct(size, size) + 2;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col)) return;

        int point = reduct(row, col);

        counter++;
        canvas[point] = true;

        if (row == 1) {
            grid.union(top, point);
            full.union(top, point);
        }
        if (row == size) {
            grid.union(bottom, point);
        }

        for (int i = 0; i < dx.length; i++) {
            int nRow = row + dx[i];
            int nCol = col + dy[i];
            if (nRow > 0 && nRow <= size && nCol > 0 && nCol <= size && isOpen(nRow, nCol)) {
                int neighbour = reduct(nRow, nCol);
                grid.union(point, neighbour);
                full.union(point, neighbour);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return canvas[reduct(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return full.connected(top, reduct(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return counter;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(top, bottom);
    }

    /**------------ below is private method ------------------*/

    // validate the point
     private void validate(int row, int col) {
         if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();
     }

     private int reduct(int row, int col) {
         return (row - 1) * size + (col - 1);
     }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1, 1);
        System.out.println(p.percolates());

        p.open(2, 1);
        p.open(3, 1);
        System.out.println(p.percolates());

        p.open(4, 1);
        System.out.println(p.percolates());

        System.out.println(p.isFull(4, 1));
        System.out.println(p.isFull(4, 2));

        p.open(4, 3);
        p.open(4, 2);
        System.out.println(p.isFull(4, 3));

        p = new Percolation(4);
        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 2);
        p.open(4, 2);
        System.out.println(p.percolates());

        p = new Percolation(4);
        p.open(1, 1);
        p.open(2, 1);
        p.open(2, 2);
        p.open(2, 3);
        p.open(3, 3);
        p.open(3, 4);
        System.out.println(p.percolates());
        p.open(4, 4);
        System.out.println(p.percolates());
    }
}
