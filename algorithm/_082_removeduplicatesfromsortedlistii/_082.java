/*
 * Copyright [2021]
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

package _082_removeduplicatesfromsortedlistii;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by woncz on 3/25/2021.
 */
public class _082 {
    public static void main(String[] args) {
        ISolution solution = new Solution();
        ListNode head = new ListNode(1);
        ListNode cur = head;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(5);

        ListNode nh = solution.deleteDuplicates(head);
        System.out.println(nh.val);
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
    ListNode deleteDuplicates(ListNode head);
}

class Solution implements ISolution {
    @Override
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        int v = head.val;
        ListNode nh = head;
        if (head.val == head.next.val) {
            ListNode cur = head.next;
            // 找出新头
            while (cur != null && v == cur.val) {
                cur = cur.next;
            }
            nh = cur;
        }

        // 顺序定岗
        if (nh != null) {
            nh.next = deleteDuplicates(nh.next);
        }
        return nh;
    }
}

class Solution2 implements ISolution {
    @Override
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;

        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}