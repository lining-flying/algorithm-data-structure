package linklist;

import java.nio.charset.CharsetEncoder;

public class Solution_1 {

    /**
     * 返回链表的中点，奇数时返回中点，偶数时返回上中点
     * @param head
     * @param <T>
     * @return
     */
    public static <T> ListNode<T> midOrUpmidNode(ListNode<T> head){
        if(head == null || head.getNext() == null || head.getNext().getNext() == null){
            return head ;
        }

        ListNode<T> slow = head.getNext();
        ListNode<T> fast = head.getNext().getNext();

        while(fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext() ;
            fast = fast.getNext().getNext() ;
        }

        return slow ;
    }

    /**
     * 判断链表中是否回文
     * 使用stack，额外空间复杂度O(N)
     */
    public static boolean isPalindrome1(ListNode<Character> head){
        //TODO
        return false ;
    }

    /**
     * 判断链表是否回文
     * 使用stack，额外空间复杂度O(N/2)
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode<Character> head){
        //TODO
        return false ;
    }

    /**
     * 判断链表是否回文
     * 额外空间复杂度O(1)
     * @param head
     * @return
     */
    public static boolean isPalindrome3(ListNode<Character> head){
        if(head == null || head.getNext() == null){
            return true ;
        }

        //快慢指针找中点（上中点）
        ListNode<Character> slow = head ;
        ListNode<Character> fast = head ;

        while(fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        //此时slow已经来到中点或者时上中点
        //从slow开始逆序
        ListNode<Character> node1 = slow ;
        ListNode<Character> node2 = slow.getNext();
        ListNode<Character> node3 = null ; //temp
        while(node2 != null){
            node3 = node2.getNext();
            node2.setNext(node1);
            node1 = node2 ;
            node2 = node3 ;
        }

        slow.setNext(null);

        //从链表两头开始遍历，判断是否相等
        node2 = head ;
        boolean res = true ;
        while(node2 != null && node1 != null){
            if(!node2.getValue().equals(node1.getValue())){
                res = false ;
                break;
            }
            node2 = node2.getNext() ;
            node1 = node1.getNext();
        }

        //此时fast指向的链表尾（即逆向链表的头）
        //恢复链表
        node1 = fast ;
        node2 = node1.getNext();
        while(node2 != null){
            node3 = node2.getNext() ;
            node2.setNext(node1);
            node1 = node2 ;
            node2 = node3 ;
        }

        fast.setNext(null);

        return res ;
    }


    /**
     * 链表分区：指定输入的数据，将链表划分为小于target在左边，大于target的在右边
     * 时间复杂度O(N),额外空间复杂度O(1)
     */
    public static ListNode<Integer> listPartition(ListNode<Integer> head,int target){
        //边界值
        if(head == null || head.getNext() == null){
            return head ;
        }

        ListNode<Integer> sHead = null ; // < head
        ListNode<Integer> sTail = null ; // < tail
        ListNode<Integer> eHead = null ; // = head
        ListNode<Integer> eTail = null ; // = tail
        ListNode<Integer> bHead = null ; // > head
        ListNode<Integer> bTail = null ; // > tail

        ListNode<Integer> node = head ;
        ListNode<Integer> temp = null ;
        while(node != null){

            if(node.getValue()<target){
                if(sHead == null){
                    sHead = node ;
                    sTail = node ;
                }else {
                    sTail = add(sTail, node);
                }
            }else if(node.getValue()>target){
                if(bHead == null) {
                    bHead = node ;
                    bTail = node ;
                }else{
                    bTail = add(bTail, node);
                }
            }else{
                if(eHead == null){
                    eHead = node;
                    eTail = node;
                }else {
                    eTail = add(eTail, node);
                }
            }

            temp = node.getNext() ;
            node.setNext(null);
            node = temp ;
        }

        head = sHead ;
        ListNode<Integer> tail = sTail ;
        if(eHead != null){
            if(tail == null){
                head = eHead ;
            }else{
                tail.setNext(eHead);
            }
            tail = eTail ;
        }

        if(bHead != null){
            if(tail == null){
                head = bHead ;
            }else{
                tail.setNext(bHead);
            }
        }

        return head ;
    }

    private static ListNode<Integer> add(ListNode<Integer> tail, ListNode<Integer> node) {
        tail.setNext(node);
        tail = node ;
        return tail ;
    }


    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        head.setNext(new ListNode<>(2));
        head.getNext().setNext( new ListNode<>(3));
        head.getNext().getNext().setNext(new ListNode<>(2));
        head.getNext().getNext().getNext().setNext(new ListNode<>(5));

//        System.out.println("result:"+isPalindrome3(head));

        listPartition(head,3);

        printf(head);
    }

    private static void printf(ListNode head){
        ListNode node = head ;
        while(node != null){
            System.out.print(node.getValue());
            System.out.print("-->");
            node = node.getNext() ;
        }
        System.out.println("null");
    }
}
