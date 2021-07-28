package common;

/**
 * @Author Constant
 * @Date 2021/7/28 22:29
 * @Description 链表类
 **/
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {};

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this.val = val;
    }
}
