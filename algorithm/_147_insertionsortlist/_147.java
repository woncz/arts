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

package _147_insertionsortlist;

/**
Created by woncz on 11/20/2020.
 */
public class _147 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode p = head;
        p.next = new ListNode(1);
        p = p.next;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(3);

        ISolution solution = new Solution();
        ListNode h = solution.insertionSortList(head);
        while (h != null) {
            System.out.print(h.val + "->");
            h = h.next;
        }

        System.out.println("");

        head = new ListNode(1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);

        h = solution.insertionSortList(head);
        while (h != null) {
            System.out.print(h.val + "->");
            h = h.next;
        }
        System.out.println("");
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

interface ISolution {
    ListNode insertionSortList(ListNode head);  
}

class Solution implements ISolution {
    @Override
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = head, cur = head.next;
        ListNode l = sort(head, pre, cur);
        return l;
    }

    ListNode sort(ListNode head, ListNode pre, ListNode cur) {
        // terminator
        if (cur == null) return head;

        ListNode next = cur.next;
        if (cur.val < pre.val) {
            ListNode h = head;
            ListNode p = null;
            while (h.val < cur.val) {
                p = h;
                h = h.next;
            }

            if (p == null) {
                cur.next = head;
                pre.next = next;
                head = cur;
            } else {
                p.next = cur;
                cur.next = h;
                pre.next = next;
            }
        } else {
            pre = pre.next;
        }

        // drill down
        return sort(head, pre, next);
    }
}