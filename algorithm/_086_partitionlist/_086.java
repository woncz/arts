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

package _086_partitionlist;

/**
Created by woncz on 1/4/2021.
 */
public class _086 {
    public static void main(String[] args) {

        ISolution solution = new Solution2();
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

interface ISolution {
    ListNode partition(ListNode head, int x);
}

class Solution implements ISolution {
    @Override
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode h1 = null, t1 = null, h2 = null, t2 = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (head.val < x) {
                if (h1 == null) {
                    h1 = head;
                    t1 = head;
                } else {
                    t1.next = head;
                    t1 = t1.next;
                }
            } else {
                if (h2 == null) {
                    h2 = head;
                    t2 = head;
                } else {
                    t2.next = head;
                    t2 = t2.next;
                }
            }
            head = next;
        }
        if (t1 != null && h2 != null) {
            t1.next = h2;
        }
        return h1 != null ? h1 : h2;
    }
}

class Solution2 implements ISolution {
    @Override
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode h = null, m = null, t = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (head.val < x) {
                if (h == null) {
                    h = head;
                    if (m == null) {
                        m = head;
                    } else {
                        h.next = m;
                        m = h;
                    }
                } else {
                    ListNode tmp = m;
                    m = head;
                    m.next = tmp.next;
                    tmp.next = m;
                }
            } else {
                if (t == null) {
                    t = head;
                    if (m == null) {
                        m = head;
                    } else {
                        m.next = head;
                    }
                } else {
                    t.next = head;
                }
            }
            head = next;
        }
        return h;
    }
}