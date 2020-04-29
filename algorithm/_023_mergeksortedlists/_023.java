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

package _023_mergeksortedlists;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


public class _023 {
    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);

        l11.next = l12;
        l12.next = l13;

        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);
        l21.next = l22;
        l22.next = l23;

        ListNode l31 = new ListNode(2);
        ListNode l32 = new ListNode(6);
        l31.next = l32;

        ISolution s= new Solution();
        ListNode[] lists = new ListNode[3];
        lists[0] = l11;
        lists[1] = l21;
        lists[2] = l31;
        ListNode head = s.mergeKLists(lists);
        System.out.println(head);
    }
}

interface ISolution {
    ListNode mergeKLists(ListNode[] lists);
}

/**
 * 小顶堆
 */
class Solution implements ISolution {
    @Override
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1 == null && o2 == null) return 0;
            if (o1 == null) return -1;
            if (o2 == null) return 1;
            if (o1.val == o2.val) {
                return 0;
            } else if (o1.val > o2.val) {
                return 1;
            } else {
                return -1;
            }
        });
        // init
        for (int i = 0; i < lists.length; i++) {
            ListNode list = lists[i];
            if (list != null) {
                pq.add(list);
            }
        }

        ListNode head = null;
        ListNode tail = null;

        // process
        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll();
            if (head == null) {
                head = smallest;
                tail = head;
            } else {
                tail.next = smallest;
                tail = tail.next;
            }
            ListNode substitute = smallest.next;
            tail.next = null;
            if (substitute != null) {
                pq.add(substitute);
            }
        }
        return head;
    }
}

/**
 * divide and conquer
 */
class Solution2 implements ISolution {
    @Override
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) return null;
        if (lists.length == 1) return lists[0];

        ListNode head = null;
        ListNode left = lists[0];
        for (int i = 1; i < lists.length; i++) {
            if (lists[i] == null) continue;
            head = this.merge(head, left, lists[i]);
        }

        return head;
    }

    private ListNode merge(ListNode head, ListNode left, ListNode right) {
        if (left == null) {
            if (head == null) {
                head = right;
            } else {
                head.next = right;
            }
            return head;
        }
        if (right == null) {
            if (head == null) {
                head = left;
            } else {
                head.next = left;
            }
            return head;
        }

        if (left.val < right.val) {
            head.next = left;
            left = left.next;
        } else {
            head.next = right;
            right = right.next;
        }
        return this.merge(head, left, right);
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}