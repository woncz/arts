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

package algo4.week5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PointSET {

    private SET<Point2D> s;

    // construct an empty set of points
    public PointSET() {
        s = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return s.isEmpty();
    }

    // number of points in the set
    public int size() {
        return s.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        s.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return s.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        Iterator<Point2D> it = s.iterator();
        while (it.hasNext()) {
            Point2D p = it.next();
            StdDraw.point(p.x(), p.y());
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        List<Point2D> points = new LinkedList<>();
        Iterator<Point2D> it = s.iterator();
        while (it.hasNext()) {
            Point2D p = it.next();
            if (p.x() >= rect.xmin() && p.x() <= rect.xmax() && p.y() >= rect.ymin() && p.y() <= rect.ymax()) {
                points.add(p);
            }
        }
        return points;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        double d = Double.MAX_VALUE;
        Point2D nearest = null;
        Iterator<Point2D> it = s.iterator();
        while (it.hasNext()) {
            Point2D point = it.next();
            double dd = point.distanceSquaredTo(p);
            if (dd < d) {
                d = dd;
                nearest = point;
            }
        }
        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        // initialize the data structures from file
        String filename = args[0];
        In in = new In(filename);
        PointSET brute = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);
        }

        for (int i = 0; i < 100; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            Point2D point = new Point2D(x, y);
            brute.insert(point);
        }

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.008);
        brute.draw();
        StdDraw.show();

    }
}
