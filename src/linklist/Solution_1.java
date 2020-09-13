package linklist;

import org.omg.CORBA.INTERNAL;

import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * 链表深度克隆
     * 方法一、 使用HashMap
     * 时间复杂度O(N)
     * 额外空间复杂度O(N)
     * @param head
     * @return
     */
    public static ListNodeWithRandom<Integer> copyListNodeWithRandom_1(ListNodeWithRandom<Integer> head){
        if(head == null){
            return null ;
        }

        Map<ListNodeWithRandom<Integer>,ListNodeWithRandom<Integer>> map = new HashMap<>();
        ListNodeWithRandom<Integer> node = head ;

        while(node != null){
            map.put(node,new ListNodeWithRandom<>(node.getValue()));
            node = node.getNext();
        }

        node = head ;
        while(node != null){
            ListNodeWithRandom<Integer> newNode = map.get(node);
            newNode.setNext(map.get(node.getNext()));
            newNode.setRand(map.get(node.getRand()));
        }

        return map.get(head);
    }


    /**
     * 链表深度复制
     * 方法二、不适用HashMap，直接在原链表上处理
     * 时间复杂度O(N) 常数项应该比方法一高？
     * 额外空间复杂度O(N)
     * @param head
     * @return
     */
    public static ListNodeWithRandom<Integer> copyListNodeWithRandom_2(ListNodeWithRandom<Integer> head){
        if(head == null){
            return null ;
        }

        ListNodeWithRandom<Integer> cur = head ;
        ListNodeWithRandom<Integer> curCopy = null ;
        ListNodeWithRandom<Integer> next = null ;

        /**
         * 第一步： 将所有节点的复制节点放到原节点的next位置
         */
        while(cur != null){
            next = cur.getNext() ;
            curCopy = new ListNodeWithRandom<>(cur.getValue());
            cur.setNext(curCopy);
            curCopy.setNext(next);
            cur = next ;
        }

        cur = head ;
        /**
         * 第二步：遍历链表，设置新节点的rand指针： 原节点的rand的复制节点
         */
        while(cur != null){
            next = cur.getNext().getNext() ;
            curCopy.setRand(cur.getRand() == null ? null : cur.getRand().getNext());
            cur = next ;
        }

        /**
         * 第三步：遍历链表，断开原节点和复制节点的关系，构造新的链表
         */
        cur = head;
        ListNodeWithRandom<Integer> copyHead = head.getNext();
        while(cur != null){
            next = cur.getNext().getNext();
            curCopy = cur.getNext(); //当前复制的节点
            cur.setNext(next); //恢复原链表节点指向
            curCopy.setNext(next != null ? next.getNext() : null); //新链表的结点指向
            cur = next ;
        }
        return copyHead ;
    }


    /**
     * 返回两个链表第一个相交的节点
     * @param head1 可能有环也可能无环
     * @param head2 可能有环也可能无环
     * @return
     */
    public static ListNode getIntersectNode(ListNode head1,ListNode head2){
        if(head1 == null || head2 == null){
            return null ;
        }

        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);

        if(loop1 == null && loop2 == null){
            return noLoop(head1,head2);
        }else if(loop1 != null && loop2 != null){
            return bothLoop(head1,loop1,head2,loop2);
        }else{
            return null ;
        }
    }


    /**
     * 判断一个链表是否有环，有环则返回入环的第一个节点，无环返回null
     * @param head
     * @return
     */
    public static ListNode getLoopNode(ListNode head){
        if(head == null || head.getNext()== null || head.getNext().getNext() == null){
            return null ;
        }

        ListNode fast = head.getNext().getNext() ;
        ListNode slow = head.getNext() ;

        while(fast != slow){
            if(fast.getNext() == null || fast.getNext().getNext() == null){
                return null ;
            }else{
                fast = fast.getNext().getNext() ;
            }
            slow = slow.getNext() ;
        }

        fast = head ;

        while(fast != slow){
            fast = fast.getNext();
            slow = slow.getNext() ;
        }

        return slow ;
    }


    /**
     * head1和head2都无环，判断二者是否相交，如果相交返回第一个相交的节点，不相交返回null
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode noLoop(ListNode head1,ListNode head2){
        if(head1 == null || head2 == null){
            return null ;
        }

        ListNode cur1 = head1 ;
        ListNode cur2 = head2 ;
        int n=0;
        while(cur1.getNext() != null){
            n++ ;
            cur1 = cur1.getNext() ;
        }
        while(cur2.getNext() != null){
            n-- ;
            cur2 = cur2.getNext() ;
        }
        if(cur1 != cur2){
            return null ;
        }
        cur1 = n>0 ? head1 : head2 ; //谁长谁变cur1
        cur2 = cur1 == head1 ? head2 : head1 ; //谁短谁变cur2
        n = Math.abs(n);
        while(n!=0){
            cur1 = cur1.getNext() ;
            n-- ;
        }
        while(cur1 != cur2){
            cur1 = cur1.getNext() ;
            cur2 = cur2.getNext() ;
        }

        return cur1 ;
    }

    /**
     * 两个链表都有环，返回相交的第一个节点
     * @param head1
     * @param loop1 head1的入环节点
     * @param head2
     * @param loop2 head2的入环节点
     * @return
     */
    public static ListNode bothLoop(ListNode head1,ListNode loop1,ListNode head2, ListNode loop2){
        ListNode cur1 = null ;
        ListNode cur2 = null ;
        if(loop1 == loop2){
            //如果loop1和loop2相等，说明相交点一定在loop1和loop2之前，则问题转化为求两个无环链表相交的第一个节点问题
            // 因为此时问题和有环无环已经没有关系了
            cur1 = head1 ;
            cur2 = head2 ;
            int n=0;
            while(cur1.getNext() != loop1){
                n++ ;
                cur1 = cur1.getNext() ;
            }
            while(cur2.getNext() != loop2){
                n-- ;
                cur2 = cur2.getNext() ;
            }
            cur1 = n>0 ? head1 : head2 ; //谁长谁变cur1
            cur2 = cur1 == head1 ? head2 : head1 ; //谁短谁变cur2
            n = Math.abs(n);
            while(n!=0){
                cur1 = cur1.getNext() ;
                n-- ;
            }
            while(cur1 != cur2){
                cur1 = cur1.getNext() ;
                cur2 = cur2.getNext() ;
            }

            return cur1 ;
        }else{

            // 如果在环上能相遇，则说明相交，此时loop1和loop2都是相交的第一个节点，无非是离head1还是head2近一点儿的问题
            cur1 = loop1.getNext() ;
            while(cur1 != loop1){
                if(cur1 == loop1){
                    return loop1 ;
                }
                cur1 = cur1.getNext() ;
            }
            // 如果在环上绕了一圈都没有相遇，则说明两个环不相交
            return null ;
        }
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
