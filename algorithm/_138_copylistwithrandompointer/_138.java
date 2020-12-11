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

package _138_copylistwithrandompointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by woncz on 8/12/2020.
 */
public class _138 {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);

        n1.next = n2;
        n1.random = n2;

        n2.random = n2;

        ISolution solution = new Solution();
        Node nn = solution.copyRandomList(n1);
        System.out.println(nn);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

interface ISolution {
    Node copyRandomList(Node head);
}

class Solution implements ISolution {
    @Override
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Integer> index = new HashMap<>();
        List<Node> cache = new ArrayList<>();

        // init head node
        Node ans = new Node(head.val);
        cache.add(ans);

        Node p = head;
        Node q = ans;

        // first round for next
        int cnt = 0;
        while (p != null) {
            index.put(p, cnt);
            if (cnt == 0) {
                ans.val = p.val;
            } else {
                Node nn = new Node(p.val);
                cache.add(nn);
                q.next = nn;
                q = q.next;
            }
            cnt++;

            p = p.next;
        }

        // second round for random
        p = head;
        q = ans;
        do {
            if (p.random != null) {
                int i = index.get(p.random);
                q.random = cache.get(i);
            }
            p = p.next;
            q = q.next;
        } while (p != null);

        return ans;
    }
}

class Solution2 implements ISolution {

    Map<Node, Node> visited = new HashMap<>();

    Node _getCloneNode(Node node) {
        if (node == null) return null;

        if (!visited.containsKey(node)) {
            visited.put(node, new Node(node.val));
        }
        return visited.get(node);
    }

    @Override
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node p = head;
        Node q = new Node(p.val);
        this.visited.put(p, q);
        while (p != null) {
            q.next = this._getCloneNode(p.next);
            q.random = this._getCloneNode(p.random);

            p = p.next;
            q = q.next;
        }
        return this.visited.get(head);
    }
}

/**
 * link the original node and the clone node temporarily, then unlink them and recover
 */
class Solution3 implements ISolution {
    @Override
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // clone node is just intersected
        Node p = head;
        while (p != null) {
            Node q = new Node(p.val);
            q.next = p.next;
            p.next = q;
            p = q.next;
        }

        // process random (make clone node's random is ok, then recover the original node)
        p = head;
        while (p != null) {
            p.next.random = p.random == null ? null : p.random.next;
            p = p.next.next; // 2 moves per step
        }

        // process next (unlink the original node and the clone node)
        Node ans = head.next;
        p = head;
        Node q = head.next;
        while (p != null) {
             p.next = p.next.next;
             q.next = q.next == null ? null : q.next.next;

             p = p.next;
             q = q.next;
        }

        return ans;
    }
}