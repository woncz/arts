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
/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver2 {
    private boolean isSolvable;
    private SearchNode cur;
    private class SearchNode implements Comparable<SearchNode>
    {
        private Board bd;
        private SearchNode pre;
        private int move;
        private int priority;

        public SearchNode(Board board, SearchNode previous) {
            bd = board;
            pre = previous;
            if (previous != null)
            {
                move = previous.move + 1;
            }
            else
            {
                move = 0;
            }
            priority = move + bd.manhattan();
        }

        @Override
        public int compareTo(SearchNode o) {
            if (priority > o.priority)
                return 1;
            else if (priority < o.priority)
                return -1;
            return 0;
        }
    }
    public Solver2(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if (initial == null)
            throw new IllegalArgumentException("initial board is null");
        MinPQ<SearchNode> searchNodeMinPQ = new MinPQ<>();
        searchNodeMinPQ.insert(new SearchNode(initial, null));
        MinPQ<SearchNode> searchTwinNodeMinPQ = new MinPQ<>();
        searchTwinNodeMinPQ.insert(new SearchNode(initial.twin(), null));
        while (true)
        {
            cur = searchNodeMinPQ.delMin();
            if (cur.bd.isGoal())
            {
                break;
            }
            for (Board neibor : cur.bd.neighbors())
                if (cur.pre == null || !neibor.equals(cur.pre.bd))
                    searchNodeMinPQ.insert(new SearchNode(neibor, cur));
            cur = searchTwinNodeMinPQ.delMin();
            if (cur.bd.isGoal())
            {
                break;
            }
            for (Board neibor : cur.bd.neighbors())
                if (cur.pre == null || !neibor.equals(cur.pre.bd))
                    searchTwinNodeMinPQ.insert(new SearchNode(neibor, cur));

        }
        SearchNode temp = cur;
        while (temp.pre != null)
            temp = temp.pre;
        if (temp.bd.equals(initial))
            isSolvable = true;
        else
            isSolvable = false;
    }

    public boolean isSolvable()            // is the initial board solvable?
    {
        return isSolvable;
    }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        if (!isSolvable)
            return -1;
        return cur.move;
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if (!isSolvable)
            return null;
        Stack<Board> solution = new Stack<>();
        SearchNode temp = cur;
        while (temp != null)
        {
            solution.push(temp.bd);
            temp = temp.pre;
        }
        return solution;
    }

    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver2 solver = new Solver2(initial);

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
