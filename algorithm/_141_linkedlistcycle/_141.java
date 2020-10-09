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

package _141_linkedlistcycle;

/**
Created by woncz on 10/9/2020.
 */
public class _141 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(0);
        ListNode l4 = new ListNode(-4);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l2;

        ISolution solution = new Solution();
        boolean circle = solution.hasCycle(l1);
        System.out.println(circle);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

interface ISolution {
    boolean hasCycle(ListNode head);
}

/**
 * fast & slow pointer
 */
class Solution implements ISolution {
    @Override
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode fast = head, slow = head;
        while (fast != null && slow != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) return false;
            fast = fast.next;

            if (slow == fast) return true;
        }

        return false;
    }
}