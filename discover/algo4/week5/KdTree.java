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

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private Node root;

    private int n;

    private class Node {
        private Point2D p;
        private Node left;
        private Node right;

        private RectHV rect;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return n;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        if (root == null) {
            root = new Node(p, new RectHV(0, 0, 1, 1));
        } else {
            insert(root, p, false);
        }
        n++;
    }

    private void insert(Node node, Point2D p, boolean flag) {
        if (node.p.equals(p)) {
            n--;
            return;
        }

        double cmp;
        if (!flag) {
            cmp = p.x() - node.p.x();
        } else {
            cmp = p.y() - node.p.y();
        }

        if (cmp < 0) {
            if (node.left != null) {
                insert(node.left, p, !flag);
            } else {
                if (flag) {
                    node.left = new Node(p, new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y()));
                } else {
                    node.left = new Node(p, new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax()));
                }
            }
        } else {
            if (node.right != null) {
                insert(node.right, p, !flag);
            } else {
                if (flag) {
                    node.right = new Node(p, new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax()));
                } else {
                    node.right = new Node(p, new RectHV(node.p.x(), node.rect.ymin(),  node.rect.xmax(), node.rect.ymax()));
                }
            }
        }

    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return find(root, p, true);
    }

    private boolean find(Node node, Point2D p, boolean flag) {
        // terminator
        if (node == null) return false;
        if (node.p.equals(p)) return true;

        double cmp;
        if (flag) cmp = p.x() - node.p.x();
        else cmp = p.y() - node.p.y();

        // drill down
        if (cmp < 0) return find(node.left, p, !flag);
        else return find(node.right, p, flag);
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.setPenRadius(0.003);
        draw(root, true);
    }

    private void draw(Node node, boolean flag) {
        if (node == null) return;

        if (flag) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(node.p.x(), node.p.y());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(node.p.x(), node.p.y());
        }
        draw(node.left, !flag);
        draw(node.right, !flag);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> list = new ArrayList<>();
        if (root != null) {
            list.addAll(range(root, rect));
        }
        return list;
    }

    private List<Point2D> range(Node node, RectHV rect) {
        List<Point2D> list = new ArrayList<>();
        if (node.rect.intersects(rect)) {
            if (rect.contains(node.p)) {
                list.add(node.p);
            }
            if (node.left != null) {
                list.addAll(range(node.left, rect));
            }
            if (node.right != null) {
                list.addAll(range(node.right, rect));
            }
        }
        return list;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return null;

        return nearest(root, p, root.p, true);
    }

    private Point2D nearest(Node node, Point2D p, Point2D closest, boolean flag) {
        double min = closest.distanceSquaredTo(p);
        if (node == null || Double.compare(node.rect.distanceSquaredTo(p), min) >= 0) return closest;
        double distance = node.p.distanceSquaredTo(p);
        if (Double.compare(distance, min) == -1) {
            closest = node.p;
        }
        if ((flag && Double.compare(p.x(), node.p.x()) < 0) || (!flag && Double.compare(p.y(), node.p.y()) < 0)) {
            closest = nearest(node.left, p, closest, !flag);
            closest = nearest(node.right, p, closest, !flag);
        } else {
            closest = nearest(node.right, p, closest, !flag);
            closest = nearest(node.left, p, closest, !flag);
        }
        return closest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
