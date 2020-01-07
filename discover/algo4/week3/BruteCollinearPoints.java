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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private Point[] points;

    private List<LineSegment> ls;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
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
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                double slopeij = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length - 1; k++) {
                    double slopeik = points[i].slopeTo(points[k]);
                    for (int l = k + 1; l < points.length; l++) {
                        double slopeil = points[i].slopeTo(points[l]);
                        if (slopeij == slopeik && slopeij == slopeil) {
                            ls.add(new LineSegment(points[i], points[l]));
                        }
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
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        int cnt = bcp.numberOfSegments();
        LineSegment[] ls = bcp.segments();
        System.out.println(cnt);
        System.out.println(Arrays.toString(ls));
    }
}