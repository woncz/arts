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

package algo4.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private boolean solvable;

    private SearchNode end;

    private class SearchNode implements Comparable<SearchNode> {

        private SearchNode previous;

        private Board board;

        private int priority;

        private int move;

        public SearchNode(Board board, SearchNode previous) {
            this.board = board;
            this.previous = previous;
            if (previous != null) {
                this.move = previous.move + 1;
            }
            priority = move + board.manhattan();
        }

        @Override
        public int compareTo(SearchNode o) {
            if (priority > o.priority) return 1;
            else if (priority < o.priority) return -1;
            else return 0;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial, null));

        MinPQ<SearchNode> twinPQ = new MinPQ<>();
        twinPQ.insert(new SearchNode(initial.twin(), null));

        while (true) {
            // terminator
            end = pq.delMin();
            if (end.board.isGoal()) {
                solvable = true;
                break;
            }
            // drill down
            for (Board neighbor : end.board.neighbors())
                if (end.previous == null || !neighbor.equals(end.previous.board))
                    pq.insert(new SearchNode(neighbor, end));

            end = twinPQ.delMin();
            if (end.board.isGoal()) {
                solvable = false;
                break;
            }
            // drill down
            for (Board neighbor : end.board.neighbors())
                if (end.previous == null || !neighbor.equals(end.previous.board))
                    twinPQ.insert(new SearchNode(neighbor, end));

        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (!isSolvable()) return -1;
        return end.move;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> solution = new Stack<>();
        SearchNode sn = end;
        while (sn != null) {
            solution.push(sn.board);
            sn = sn.previous;
        }
        return solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
