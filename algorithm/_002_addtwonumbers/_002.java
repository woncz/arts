package _002_addtwonumbers;

/**
 * @author woncz
 * @date 7/24/2019.
 */
public class _002 {
    public static void main(String[] args) {
        Solution s = new Solution();

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode l = s.addTwoNumbers(l1, l2);
        while (l != null) {
            System.out.print(l.val + "->");
            l = l.next;
        }
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            throw new IllegalArgumentException("illegal argument");
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // head
        int low = 0;
        int t = l1.val + l2.val;
        if (t > 9) {
            low = 1;
            t -= 10;
        }
        ListNode head = new ListNode(t);

        ListNode first = l1.next;
        ListNode second = l2.next;

        ListNode n = head;

        while (first != null || second != null || low == 1) {
            int x = first == null ? 0 : first.val;
            int y = second == null ? 0 : second.val;
            int total = x + y + low;
            // reset
            low = 0;
            if (total <= 9) {
                n.next = new ListNode(total);
            } else {
                n.next = new ListNode(total - 10);
                low = 1;
            }

            // forward
            first = first == null ? null : first.next;
            second = second == null ? null : second.next;
            n = n.next;
        }

        return head;
    }
}