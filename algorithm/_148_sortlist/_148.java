/*
Copyright [2020]
 *
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 *
  http://www.apache.org/licenses/LICENSE-2.0
 *
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package _148_sortlist;

/**
Created by woncz on 11/22/2020.
 */
public class _148 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);

        ISolution solution = new Solution();
        ListNode h = solution.sortList(head);
        while (h != null) {
            System.out.print(h.val + "->");
            h = h.next;
        }

        System.out.println();

        head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);
        h = solution.sortList(head);
        while (h != null) {
            System.out.print(h.val + "->");
            h = h.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

interface ISolution {
    ListNode sortList(ListNode head);
}

class Solution implements ISolution {
    @Override
    public ListNode sortList(ListNode head) {
        // 0. defence coding
        if (head == null || head.next == null) return head;

        // 1. find the middle node and divide the list into two parts
        ListNode pre = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // unlink
        pre.next = null;

        // 2. sort each part
        ListNode l = sortList(head);
        ListNode r = sortList(slow);

        // 3. merge l and r
        return merge(l, r);
    }

    ListNode merge(ListNode l, ListNode r) {
        ListNode dummy = new ListNode(0), p = dummy;
        while (l != null && r != null) {
            if (l.val < r.val) {
                p.next = l;
                l = l.next;
            } else {
                p.next = r;
                r = r.next;
            }
            // forward
            p = p.next;
        }

        if (l != null) {
            p.next = l;
        }
        if (r != null) {
            p.next = r;
        }
        return dummy.next;
    }
}

/**
 * timeout O(N^2)
 */
class Solution2 implements ISolution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        for (ListNode p = head; p != null;) {
            ListNode q = p.next;
            p.next = null;
            sort(dummy, p);
            p = q;
        }
        return dummy.next;
    }

    void sort(ListNode h, ListNode p) {
        ListNode l = h, r = l.next;
        while (r != null && r.val < p.val) {
            r = r.next;
            l = l.next;
        }
        if (l.next == null) {
            l.next = p;
        } else {
            p.next = l.next;
            l.next = p;
        }
    }
}
