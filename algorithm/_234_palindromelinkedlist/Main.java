
package _234_palindromelinkedlist;

import java.util.Stack;

/**
 * @author woncz
 * @date 2019/10/23
 */
public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        Solution1 s1 = new Solution1();
        boolean b = s1.isPalindrome(head);
        System.out.println(b);

        ISolution s2 = new Solution2();
        b = s2.isPalindrome(head);
        System.out.println(b);
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
    boolean isPalindrome(ListNode head);
}

/**
 * 暴力法
 * time complexity : O(N)
 * space complexity : O(N)
 */
class Solution1 implements ISolution {
    @Override
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        Stack<ListNode> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            stack.add(current);
            current = current.next;
        }
        current = head;
        int len = stack.size() / 2;
        for (int i = 0; i < len; i++) {
            if (current.val != stack.pop().val) return false;
            current = current.next;
        }
        return true;
    }
}

/**
 * 双指针法(快慢指针)
 */
class Solution2 implements ISolution {
    @Override
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // 快慢指针找到中点(注意总节点数的奇偶性)
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 翻转前半部分(不一定非要翻转后半部分；另外可以让第一个节点的next指向null，哨兵编程简化代码逻辑)
        ListNode pre = null;
        while (head != slow) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        // 处理奇偶性(此时slow，奇数时指向正中点，偶数时指向N/2处)
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (pre.val != slow.val) return false;
            pre = pre.next;
            slow = slow.next;
        }
        return true;

        // 是否需要恢复输入数据状态？
    }
}
