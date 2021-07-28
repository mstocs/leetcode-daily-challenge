package tag.listnode;

import common.ListNode;

/**
 * @Author Constant
 * @Date 2021/7/28 22:27
 * @Description https://leetcode-cn.com/problems/add-two-numbers/
 **/
public class TwoSum {
    //思路：将链表节点值一一相加，大于10，记录carry=1
    static class Solution1 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int carry = 0;
            ListNode ans = new ListNode();
            ListNode dummyNode = new ListNode();
            dummyNode.next = ans;
            while(l1!=null || l2!=null) {
                int val;
                if(l1==null) {
                    val = l2.val + carry;
                    l2 = l2.next;
                } else if(l2 == null) {
                    val = l1.val + carry;
                    l1 = l1.next;
                } else {
                    val = l1.val + l2.val + carry;
                    l2 = l2.next;
                    l1 = l1.next;
                }
                int nodeValue = val % 10;
                carry = val > 9 ? 1 : 0;
                ListNode node = new ListNode(nodeValue);
                ans.next = node;
                ans = ans.next;
                if(l1==null && l2==null && carry == 1) {
                    ans.next = new ListNode(1);
                }
            }
            return dummyNode.next.next;
        }
    }

    static class Solution2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = null, tail = null;
            int carry = 0;
            while(l1 != null || l2 != null) {
                int n1 = l1 == null ? 0 : l1.val;
                int n2 = l2 == null ? 0 : l2.val;
                int sum = n1 + n2 + carry;
                if(head == null) {
                    head = tail = new ListNode(sum % 10);
                } else {
                    tail.next = new ListNode(sum % 10);
                    tail = tail.next;
                }
                carry = sum / 10;
                if(l1 != null) {
                    l1 = l1.next;
                }
                if(l2 != null) {
                    l2 = l2.next;
                }
            }
            if(carry > 0) {
                tail.next = new ListNode(carry);
            }
            return head;
        }
    }
}

