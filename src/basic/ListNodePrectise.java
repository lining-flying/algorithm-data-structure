package basic;

import datastructure.linklist.ListNode;

public class ListNodePrectise {

    /**
     * 删除倒数第n个节点
     * 假设n必须合法
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head,int n){
        ListNode fast = head ;
        int i=1 ;
        while(i<n){
            fast = fast.getNext() ;
            i++ ;
        }

        ListNode slow = head ;
        ListNode prev = null ;
        while(fast.getNext() != null){
            fast = fast.getNext() ;
            slow = slow.getNext() ;
            if(prev == null){
                prev = head ;
            }else{
                prev = prev.getNext() ;
            }
        }

        if(prev == null){
            head = head.getNext() ;
        }else{
            prev.setNext(slow.getNext());
        }

        return head ;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(2));
        head.getNext().setNext(new ListNode(3));
        head.getNext().getNext().setNext(new ListNode(4));
        head.getNext().getNext().getNext().setNext(new ListNode(5));

        head = removeNthFromEnd(head,5);

        while(head != null){
            System.out.print(head.getValue() + "->");
            head = head.getNext() ;
        }
    }
}
