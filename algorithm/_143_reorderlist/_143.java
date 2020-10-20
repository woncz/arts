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

package _143_reorderlist;

/**
Created by woncz on 10/20/2020.
 */
public class _143 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ISolution solution = new Solution();
        solution.reorderList(l1);
        ListNode h = l1;
        while (h != null) {
            System.out.println(h.val);
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
    void reorderList(ListNode head);
}

class Solution implements ISolution {
    @Override
    public void reorderList(ListNode head) {
        // 空节点或者只有一个节点
        if (head == null || head.next == null) return;

        // 快慢双指针走到队尾
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 逆转后半部分，形成两个列表
        ListNode tail = slow.next;
        slow.next = null; // 断开连接
        slow = null;
        while (tail.next != null) {
            ListNode cur = tail;
            tail = tail.next;
            cur.next = slow;
            slow = cur;
        }
        tail.next = slow;

        // 链接两部分
        ListNode l = head, r = tail;
        while (l != null && r != null) {
            ListNode lp = l, rp = r;
            l = l.next; r = r.next;
            lp.next = rp;
            if (l != null) {
                rp.next = l;
            }
        }
    }
}