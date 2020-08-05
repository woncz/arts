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

package _019_removenthnodefromendoflist;

/**
 Created by woncz on 8/2/2020.
 */
public class _019 {
    public static void main(String[] args) {
        ListNode head = _build();
        ISolution solution = new Solution();
        ListNode h = solution.removeNthFromEnd(head, 0);
        ListNode cur = h;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    static ListNode _build() {
        ListNode l1 = new ListNode(10);
        ListNode l2 = new ListNode(20);
        ListNode l3 = new ListNode(30);
        ListNode l4 = new ListNode(40);
        ListNode l5 = new ListNode(50);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        return l1;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

interface ISolution {
    ListNode removeNthFromEnd(ListNode head, int n);
}

class Solution implements ISolution {
    @Override
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0 || head == null) {
            return head;
        }
        ListNode pre = head, cur = head;
        int len = 1;
        while (cur.next != null) {
            len++;
            if (len - n > 1) {
                pre = pre.next;
            }
            cur = cur.next;
        }
        if (len == n) {
            head = head.next;
        }
        if (len > n) {
            pre.next = pre.next.next;
        }
        return head;
    }
}
