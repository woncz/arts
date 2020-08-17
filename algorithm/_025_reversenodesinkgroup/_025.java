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

package _025_reversenodesinkgroup;

/**
Created by woncz on 8/14/2020.
 */
public class _025 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;

        ISolution solution = new Solution();
        System.out.println(solution.reverseKGroup(l1, 2));
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

interface ISolution {
    ListNode reverseKGroup(ListNode head, int k);
}


class Solution implements ISolution {
    @Override
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;

        int cnt = k;
        ListNode ans = head;
        if (enough(head, k)) {
            while (--cnt > 0) {
                ans = ans.next;
            }
        }

        ListNode p1 = head;

        ListNode ho = null, to = null;
        while (enough(p1, k)) {
            ListNode h = p1, t = p1;
            for (int i = 1; i < k; i++) {
                ListNode n = t.next;
                t.next = n.next;
                n.next = h;
                h = n;
            }
            p1 = t.next;

            if (ho != null) {
                to.next = h;
                t.next = ho;
            }
            ho = h;
            to = t;
        }
        to.next = p1;
        return ans;
    }

    boolean enough(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k = k - 1;
        }
        return k == 0;
    }

}