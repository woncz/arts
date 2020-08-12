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

package _133_clonegraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by woncz on 8/12/2020.
 */
public class _133 {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.neighbors.add(n2);
        n1.neighbors.add(n4);

        n2.neighbors.add(n1);
        n2.neighbors.add(n3);

        n3.neighbors.add(n2);
        n3.neighbors.add(n4);

        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

        ISolution solution = new Solution();
        Node nn = solution.cloneGraph(n1);
        System.out.println(nn);
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

interface ISolution {
    Node cloneGraph(Node node);
}

class Solution implements ISolution {

    @Override
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (node.neighbors.isEmpty()) return new Node(node.val);

        return this._bfs(node, new HashMap<>());
    }

    Node _bfs(Node node, Map<Integer, Node> visited) {
        Node ans = new Node();
        Queue<Node> q1 = new ArrayDeque<>();
        Queue<Node> q2 = new ArrayDeque<>();

        q1.add(node);
        q2.add(ans);

        while (!q1.isEmpty()) {
            Node cur1 = q1.remove();
            Node cur2 = q2.remove();

            visited.put(cur1.val, cur2);
            cur2.val = cur1.val;

            for(Node n : cur1.neighbors) {
                Node nn = visited.get(n.val);
                if (nn == null) {
                    nn = new Node(n.val);
                    visited.put(n.val, nn);
                    q1.add(n);
                    q2.add(nn);
                }
                cur2.neighbors.add(nn);
            }
        }

        return ans;
    }
}

class Solution2 implements ISolution {
    @Override
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (node.neighbors.isEmpty()) return new Node(node.val);

        return this._bfs(node, new HashMap<>());
    }

    Node _bfs(Node node, Map<Integer, Node> visited) {
        Node ans = new Node(node.val);
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        visited.put(node.val, ans);

        while (!queue.isEmpty()) {
            Node cur1 = queue.remove();
            Node cur2 = visited.get(cur1.val);

            for (Node n : cur1.neighbors) {
                Node nn = visited.get(n.val);
                if (nn == null) {
                    nn = new Node(n.val);
                    visited.put(n.val, nn);
                    queue.add(n);
                }
                cur2.neighbors.add(nn);
            }
        }

        return ans;
    }
}