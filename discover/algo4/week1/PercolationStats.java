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

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean;

    private final double stddev;

    private final double confidenceLo;

    private final double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        double[] threshold = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                // [0, n * n)
                int point = StdRandom.uniform(n * n);
                int nRow = point / n + 1;
                int nCol = point % n + 1;
                percolation.open(nRow, nCol);
            }
            threshold[i] = (double) percolation.numberOfOpenSites() / ( n * n);
        }

        mean = StdStats.mean(threshold);
        stddev = StdStats.stddev(threshold);
        double nn = Math.sqrt(trials);
        confidenceHi = mean + 1.96 * stddev / nn;
        confidenceLo = mean - 1.96 * stddev / nn;
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        long start = System.currentTimeMillis();
        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean                        = " + stats.mean());
        System.out.println("stddev                      = " + stats.stddev());
        System.out.println("95% confidence interval     = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");

        long end = System.currentTimeMillis();
        System.out.println("total cost = " + (end - start) / 1000.0 + " seconds");
    }
}
