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

package algo4.week3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private Point[] points;

    private List<LineSegment> ls;

    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        if (points == null) throw new IllegalArgumentException();
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            this.points[i] = points[i];
        }
        Arrays.sort(this.points);

        for (int i = 1; i < this.points.length; i++) {
            if (this.points[i].compareTo(this.points[i - 1]) == 0) throw new IllegalArgumentException();
        }

        matching();
    }

    private void matching() {
        ls = new ArrayList<>();
        Point[] tmp = Arrays.copyOf(points, points.length);
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(tmp, points[i].slopeOrder());
            int cnt = 2;
            Point min = points[i];
            Point max = points[i];
            for (int j = 0; j < points.length - 1; j++) {
                if (points[i].slopeTo(tmp[j]) == points[i].slopeTo(tmp[j + 1])) {
                    if (tmp[j + 1].compareTo(max) > 0) {
                        max = tmp[j + 1];
                    } else if (tmp[j + 1].compareTo(min) < 0) {
                        min = tmp[j + 1];
                    }
                    cnt++;
                    // corner case
                    if (j == points.length - 2 && cnt >= 4 && points[i].compareTo(min) == 0) {
                        ls.add(new LineSegment(min, max));
                    }
                } else {
                    if (cnt >= 4 && points[i].compareTo(min) == 0) {
                        ls.add(new LineSegment(min, max));
                    }

                    // init for next loop
                    cnt = 2;
                    min = points[i];
                    max = tmp[j + 1];
                    if (points[i].compareTo(tmp[j + 1]) > 0) {
                        max = points[i];
                        min = tmp[j + 1];
                    }
                }
            }
        }
    }

    public int numberOfSegments()        // the number of line segments
    {
        return ls.size();
    }

    public LineSegment[] segments()                // the line segments
    {
        return ls.toArray(new LineSegment[ls.size()]);
    }

    public static void main(String[] args) {
        Point[] points = new Point[] {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(1, 2)};
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        int cnt = collinear.numberOfSegments();
        LineSegment[] ls = collinear.segments();
        System.out.println(cnt);
        System.out.println(Arrays.toString(ls));

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
