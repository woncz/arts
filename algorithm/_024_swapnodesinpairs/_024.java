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

package _024_swapnodesinpairs;

/**
Created by woncz on 8/14/2020.
 */
public class _024 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        ISolution solution = new Solution();
        ListNode h = solution.swapPairs(l1);
        System.out.println(h);

        l3.next = null;
        System.out.println(h);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

interface ISolution {
    ListNode swapPairs(ListNode head);
}

/**
 * 迭代
 */
class Solution implements ISolution {
    @Override
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p1, p2;
        p1 = head;
        head = p1.next;
        while (p1 != null && p1.next != null) {
            p2 = p1.next;

            p1.next = p2.next;
            p2.next = p1;
            if (p1.next != null && p1.next.next != null) {
                ListNode tail = p1;
                p1 = p1.next;
                p2 = p1.next;
                tail.next = p2;
            } else {
                p1 = p1.next;
            }
        }
        return head;
    }
}

/**
 * 递归
 */
class Solution2 implements ISolution {
    @Override
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p1 = head;
        ListNode p2 = head.next;
        p2.next = p1;
        p1.next = swapPairs(p2.next);
        return p2;
    }
}