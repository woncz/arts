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

package _160_intersectionoftwolinkedlists;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by woncz on 6/4/2021.
 */
public class _160 {
    public static void main(String[] args) {
        ListNode headA = new ListNode(8);
        ListNode headB = new ListNode(4);
        ListNode n22 = new ListNode(1);
        ListNode n24 = new ListNode(4);
        ListNode n25 = new ListNode(5);

        headB.next = n22;
        n22.next = headA;
        headA.next = n24;
        n24.next = n25;

        ISolution solution = new Solution2();
        System.out.println(solution.getIntersectionNode(headA, headB).val);
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
    ListNode getIntersectionNode(ListNode headA, ListNode headB);
}

class Solution implements ISolution {
    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> reached = new HashSet<>();
        ListNode p1 = headA, p2 = headB;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                if (reached.contains(p1)) return p1;
                reached.add(p1);
                p1 = p1.next;
            }

            if (p2 != null) {
                if (reached.contains(p2)) return p2;
                reached.add(p2);
                p2 = p2.next;
            }
        }

        return null;
    }
}

class Solution2 implements ISolution {
    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int delta = 0;
        ListNode p1 = headA, p2 = headB;
        while (p1 != null || p2 != null) {
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
            if (p1 == null && p2 != null) delta--;
            if (p1 != null && p2 == null) delta++;
        }

        p1 = headA;
        p2 = headB;
        while (delta > 0) {
            p1 = p1.next;
            delta--;
        }
        while (delta < 0) {
            p2 = p2.next;
            delta++;
        }
        // same starting line
        while (p1 != null && p2 != null) {
            if (p1 == p2) return p1;
            p1 = p1.next;
            p2 = p2.next;
        }

        return null;
    }
}

class Solution3 implements ISolution {
    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // A + B  vs  B + A, they will meet in the first node, or to the end
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }
}