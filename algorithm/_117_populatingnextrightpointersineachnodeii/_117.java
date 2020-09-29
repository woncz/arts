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

package _117_populatingnextrightpointersineachnodeii;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by woncz on 9/28/2020.
 */
public class _117 {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(7);
        n1.left = n2; n1.right = n3;
        n2.left = n4; n2.right = n5;
        n3.right = n6;

        ISolution solution = new Solution2();
        Node r = solution.connect(n1);
        System.out.println(r);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

interface ISolution {
    Node connect(Node root);
}

class Solution implements ISolution {
    @Override
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> l1 = new LinkedList<>();
        Queue<Node> l2 = new LinkedList<>();
        l1.add(root);

        while (!l1.isEmpty()) {
            Node n = l1.poll();
            Node next = null;
            if (!l1.isEmpty()) {
                next = l1.peek();
            }
            n.next = next;

            if (n.left != null) l2.add(n.left);
            if (n.right != null) l2.add(n.right);

            if (l1.isEmpty()) {
                l1.addAll(l2);
                l2.clear();
            }
        }

        return root;
    }
}

/**
 * 构建下一层横向指针
 */
class Solution2 implements ISolution {

    Node first, last;

    @Override
    public Node connect(Node root) {
        if (root == null) return null;

        first = last = root;
        Node p = first;
        while (p != null) {
            if (p == first) {
                first = last = null;
            }
            if (p.left != null) {
                this._link(p.left);
            }
            if (p.right != null) {
                this._link(p.right);
            }
            p = p.next;
            if (p == null) {
                p = first;
            }
        }

        return root;
    }

    void _link(Node node) {
        if (first == null) {
            first = node;
        }
        if (last != null) {
            last.next = node;
        }
        last = node;
    }
}
